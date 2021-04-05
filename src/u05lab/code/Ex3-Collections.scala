package u05lab.code

import u05lab.code.PerformanceUtils.{MeasurementResults, measure}

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
  case class CRUD[+R, +U, +D](colName:String, read: () => R, update: () => U, delete: () => D)

  case class Operation[+O]( name:String, op: () => O)

  def compareOn[O](description:String, operations:Operation[O]*) = {
    var results:scala.List[(String, MeasurementResults[O])] = scala.List();
    println("Compare collections on " + description)
    for(c <- operations) {
      results = (c.name, measure{c.op()}) :: results
    }
    results = results.sortBy(r => r._2.duration)
    printRanking(results)
  }

  private def printRanking[O](results:scala.List[(String, MeasurementResults[O])]): Unit ={
    println("Ranking")
    for (i <- 0 to results.size-1) println( (i+1) + ". " + results(i)._1 + " exec time: " + results(i)._2.duration)
    println("--------------------")
  }
}

object CollectionsTest extends App {
  import CRUDFunctions._
  import PerformanceUtils._
  var list = scala.List(10, 20, 30, 40, 50) //create op
  val listBuffer = ListBuffer(10, 20, 30, 40, 50) //create op
  var vector = Vector(10, 20, 30, 40, 50)
  var arr = Array(10, 20, 30, 40, 50)
  val arrBuf = ArrayBuffer(10, 20, 30, 40, 50)

  compareOn("getting the 3rd element:", Operation("List", () => list(3)),
    Operation("ListBuffer", () => listBuffer(3)), Operation("Array", () => arr(3)),
    Operation("Vector", () => vector(3)), Operation("ArrayBuffer", () => arrBuf(3)))

  compareOn("getting the size:", Operation("List", () => list.size),
    Operation("ListBuffer", () => listBuffer.size), Operation("Array", () => arr.size),
    Operation("Vector", () => vector.size), Operation("ArrayBuffer", () => arrBuf.size))

  compareOn("getting the last element:", Operation("List", () => list.last),
    Operation("ListBuffer", () => listBuffer.last), Operation("Array", () => arr.last),
    Operation("Vector", () => vector.last), Operation("ArrayBuffer", () => arrBuf.last))

  compareOn("updating by prepend a new element:", Operation("List", () => list = 0 :: list),
    Operation("ListBuffer", () => 0 +=: listBuffer), Operation("Array", () => arr = 0+:arr),
    Operation("Vector", () => vector = 0+: vector), Operation("ArrayBuffer", () => 0 +=: arrBuf))

//  measure("list append elem"){listCRUD.update()}
//  measure("listBuffer append elem"){lBufCRUD.update()}
//  measure("vector append elem"){vecCRUD.update()}
//  measure("array append elem"){arrCRUD.update()}
//
//  measure("list remove head"){listCRUD.delete()}
//  measure("listBuffer remove head"){lBufCRUD.delete()}
//  measure("vector remove head"){vecCRUD.delete()}
//  measure("array append elem"){arrCRUD.update()}



//  val lst = (1 to 1000000).toList
//  val lstCRUD = CRUD(() => lst.last, () => lst :+ 6, () => lst.drop(1))
//  val vec = (1 to 1000000).toVector
//  val vecCRUD = CRUD(() => vec.last, () => vec :+ 6, () => vec.drop(1))
//  assert( measure("lst last"){ lstCRUD.read() } > measure("vec last"){ vecCRUD.read() } )
}