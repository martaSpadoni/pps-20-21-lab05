package lab05

import lab05.PerformanceUtils.{MeasurementResults, measure}

import java.util.concurrent.TimeUnit
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.concurrent.duration.FiniteDuration

object PerformanceUtils {
  case class MeasurementResults[T](result: T, duration: FiniteDuration) extends Ordered[MeasurementResults[_]] {
    override def compare(that: MeasurementResults[_]): Int = duration.toNanos.compareTo(that.duration.toNanos)
  }

  def measure[T](msg: String)(expr: => T): MeasurementResults[T] = {
    val startTime = System.nanoTime()
    val res = expr
    val duration = FiniteDuration(System.nanoTime()-startTime, TimeUnit.NANOSECONDS)
    if(!msg.isEmpty) println(msg + " -- " + duration.toNanos + " nanos; " + duration.toMillis + "ms")
    MeasurementResults(res, duration)
  }

  def measure[T](expr: => T): MeasurementResults[T] = measure("")(expr)
}

object CRUDFunctions {

  case class Operation[+O]( name:String, op: () => O)

  def compareOn[O](description:String, operations:Operation[O]*) = {
    var results:List[(String, MeasurementResults[O])] = List();

    println("Compare collections on " + description)
    for(c <- operations) {
      results = (c.name, measure{c.op()}) :: results
    }

    results = results.sortBy({case (_,r) => r.duration})
    printRanking(results)
  }

  private def printRanking[O](results:List[(String, MeasurementResults[O])]): Unit ={
    println("Ranking")
    for (i <- 0 to results.size-1) println( (i+1) + ". " + results(i)._1 + " exec time: " + results(i)._2.duration)
    println("--------------------")
  }
}

object CollectionsTest extends App {
  import CRUDFunctions._
  var list = List(10, 20, 30, 40, 50) //create op
  val listBuffer = ListBuffer(10, 20, 30, 40, 50) //create op
  var vector = Vector(10, 20, 30, 40, 50)
  var arr = Array(10, 20, 30, 40, 50)
  val arrBuf = ArrayBuffer(10, 20, 30, 40, 50)

  var set = Set(10,20,30,40,50)
  val mutableSet = collection.mutable.Set(10, 20, 30, 40, 50)

  var map = Map(10 -> "a", 20 -> "b", 30 -> "c", 40 -> "d")
  var mutableMap = collection.mutable.Map(10 -> "a", 20 -> "b", 30 -> "c", 40 -> "d")

  compareOn("getting the 3rd element:", Operation("List", () => list(3)),
    Operation("ListBuffer", () => listBuffer(3)), Operation("Array", () => arr(3)),
    Operation("Vector", () => vector(3)), Operation("ArrayBuffer", () => arrBuf(3)))

  compareOn("getting the size:", Operation("List", () => list.size),
    Operation("ListBuffer", () => listBuffer.size), Operation("Array", () => arr.size),
    Operation("Vector", () => vector.size), Operation("ArrayBuffer", () => arrBuf.size))

  compareOn("getting the last element:", Operation("List", () => list.last),
    Operation("ListBuffer", () => listBuffer.last), Operation("Array", () => arr.last),
    Operation("Vector", () => vector.last), Operation("ArrayBuffer", () => arrBuf.last))

  compareOn("updating by prepending a new element:", Operation("List", () => list = 0 :: list),
    Operation("ListBuffer", () => 0 +=: listBuffer), Operation("Array", () => arr = 0+:arr),
    Operation("Vector", () => vector = 0+: vector), Operation("ArrayBuffer", () => 0 +=: arrBuf))

  compareOn("updating by appending a new element:", Operation("List", () => list = list :+6),
    Operation("ListBuffer", () => listBuffer += 6), Operation("Array", () => arr = arr:+6),
    Operation("Vector", () => vector = vector:+6), Operation("ArrayBuffer", () => arrBuf += 6))

  compareOn("removing first element:", Operation("List", () => list = list.drop(1)),
    Operation("ListBuffer", () => listBuffer.remove(0)), Operation("Array", () => arr = arr.drop(0)),
    Operation("Vector", () => vector = vector.drop(0)), Operation("ArrayBuffer", () => arrBuf.remove(0)))

  compareOn("removing an element:", Operation("List", () => list = list.diff(List(10))),
    Operation("ListBuffer", () => listBuffer-=10), Operation("Array", () => arr = arr.diff(Array(10))),
    Operation("Vector", () => vector = vector.diff(Vector(10))), Operation("ArrayBuffer", () => arrBuf-=10))

  compareOn("getting the size", Operation("Set", () => set.size),
    Operation("Mutable Set", () => mutableSet.size))

  compareOn("updating by insert an element", Operation("Set", () => set = set + 60),
    Operation("Mutable Set", () => mutableSet += 60))

  compareOn("removing an element", Operation("Set", () => set = set.drop(1)),
    Operation("Mutable Set", () => mutableSet.drop(1)))

  compareOn("getting a value by key", Operation("Map", () => map(10)),
    Operation("Mutable Map", () => mutableMap(10)))

  compareOn("inserting a new entry", Operation("Map", () => map = map + (50 -> "e")),
    Operation("Mutable Map", () => mutableMap += (50 -> "e")))

  compareOn("removing the entry with key 20", Operation("Map", () => map = map.filterKeys(k => k != 20) ),
    Operation("Mutable Map", () => mutableMap -= 20))

//  val lst = (1 to 1000000).toList
//  val lstCRUD = CRUD(() => lst.last, () => lst :+ 6, () => lst.drop(1))
//  val vec = (1 to 1000000).toVector
//  val vecCRUD = CRUD(() => vec.last, () => vec :+ 6, () => vec.drop(1))
//  assert( measure("lst last"){ lstCRUD.read() } > measure("vec last"){ vecCRUD.read() } )
}