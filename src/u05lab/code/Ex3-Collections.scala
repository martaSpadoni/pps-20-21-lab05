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

  case class Operation[+O]( description:String, op: () => O)

  def compare[O](operations:Operation[O]*) = {
    var results:scala.List[(String, MeasurementResults[O])] = scala.List();
    for(c <- operations) {
      results = (c.description, measure(c.description) {c.op()}) :: results
    }
    println(results.sortBy(m => m._2.duration))
  }
}

object CollectionsTest extends App {
  import CRUDFunctions._
  import PerformanceUtils._
  var list = scala.List(10, 20, 30, 40, 50) //create op
  val listCRUD = CRUD("List", () => list(3), () => list = list :+ 6, () => list = list.drop(1))
  val listBuffer = ListBuffer(10, 20, 30, 40, 50) //create op
  val lBufCRUD = CRUD("ListBuffer", ()=> listBuffer(3), () => listBuffer+=6, () => listBuffer.remove(0))
  var vector = Vector(10, 20, 30, 40, 50)
  val vecCRUD = CRUD("Vector",() => vector(3), ()=> vector = vector:+6,() => vector = vector.drop(1))
  var arr = Array(10, 20, 30, 40, 50)
  val arrCRUD = CRUD("Array",() => arr(3), ()=> arr = arr:+6,() => arr = arr.drop(1))

  compare(Operation("List read", () => list(3)), Operation("ListBuffer read", () => listBuffer(3)))

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