package u05lab.code

import u05lab.code.ExamResult._
import u05lab.code.Kind._

trait Kind
object Kind {
  case class RETIRED() extends Kind {
    override def toString: String = "RETIRED"
  }
  case class FAILED() extends Kind{
    override def toString: String = "FAILED"
  }
  case class SUCCEEDED() extends Kind{
    override def toString: String = "SUCCEEDED"
  }
}

trait ExamResult{
  def kind:Kind
  def evaluation:Option[Int]
  def cumLaude:Boolean

  override def toString: String = kind match {
    case SUCCEEDED() if cumLaude => SUCCEEDED + "(30L)"
    case SUCCEEDED() => SUCCEEDED + "(" + evaluation.get + ")"
    case _ => kind.toString
  }
}

private class ExamResultImpl (val kind: Kind, val evaluation:Option[Int], val cumLaude:Boolean) extends ExamResult

object ExamResult{

  def apply(kind: Kind, eval: Option[Int] = Option.empty,
            cumLaude:Boolean = false): ExamResult = new ExamResultImpl(kind, eval, cumLaude)
  def failed: ExamResult = ExamResult(FAILED())
  def retired: ExamResult = ExamResult(RETIRED())
  def succeeded(evaluation: Int): ExamResult = ExamResult(SUCCEEDED(), Some(evaluation))
  def succeededCumLaude : ExamResult = ExamResult(SUCCEEDED(), Some(30), true)
}

sealed trait ExamsManager {
  def createNewCall(call:String):Unit
  def addStudentResult(call:String, student:String, result:ExamResult)
  def getAllStudentsFromCall(call: String):Set[String]
  def getEvaluationsMapFromCall(call:String): Map[String,Int]
  def getResultsMapFromStudent(student:String): Map[String, String]
  def getBestResultFromStudent(student:String): Option[Int]
}

class ExamsManagerImpl() extends ExamsManager {

  private var map : Map[String, Map[String, ExamResult]] = Map()

  override def createNewCall(call: String): Unit = {
    if (map.contains(call)) throw new IllegalArgumentException
    map = map + (call -> Map())
  }

  override def addStudentResult(call: String, student: String, result: ExamResult): Unit = {
    val m = map.getOrElse(call, throw new IllegalArgumentException)
    if (m.contains(student)) throw new IllegalArgumentException
    map = map + (call -> (m + (student -> result)))
  }

  override def getAllStudentsFromCall(call: String): Set[String] = map(call).keySet

  override def getEvaluationsMapFromCall(call: String): Map[String, Int] = {
    val m = map.getOrElse(call, throw new IllegalArgumentException)
    m.collect({case(key, res) if res.evaluation.isDefined => key -> res.evaluation.get})
  }

  override def getResultsMapFromStudent(student: String): Map[String, String] =
    map.collect({case(call, results) if results.isDefinedAt(student) &&
      results(student).evaluation.isDefined => call -> results(student).evaluation.get.toString})

  override def getBestResultFromStudent(student: String): Option[Int] = {
    val results = map.values.flatMap(m => m.filterKeys(s => s == student)).map{case (_, r) => r.evaluation}
    if(!results.isEmpty) results.max else None
  }
}

object ExamsManager{
  def apply(): ExamsManager = new ExamsManagerImpl()
}

object ExamsManagerTest extends App {

  println(ExamResult.failed.kind) //FAILED
  println(ExamResult.failed.evaluation.isDefined) //false
  println(ExamResult.failed.cumLaude) //false
  println(ExamResult.failed.toString) //FAILED
  println()
  println(ExamResult.retired.kind) //RETIRED
  println(ExamResult.retired.evaluation.isDefined) //false
  println(ExamResult.retired.cumLaude) //false
  println(ExamResult.retired.toString) //RETIRED
  println()
  println(ExamResult.succeeded(28).kind) //SUCCEEDED()
  println(ExamResult.succeeded(28).evaluation) //Some(28)
  println(ExamResult.succeeded(28).cumLaude) //false
  println(ExamResult.succeeded(28).toString) //SUCCEEDED(28)
  println()
  println(ExamResult.succeededCumLaude.kind) //SUCCEEDED()
  println(ExamResult.succeededCumLaude.evaluation) //Some(30)
  println(ExamResult.succeededCumLaude.cumLaude) //true
  println(ExamResult.succeededCumLaude.toString) //SUCCEEDED(30L)

  val em : ExamsManager = ExamsManager()

  em.createNewCall("gennaio")
  em.createNewCall("febbraio")
  em.createNewCall("marzo")

  em.addStudentResult("gennaio", "rossi", ExamResult.failed) // rossi -> fallito
  em.addStudentResult("gennaio", "bianchi", ExamResult.retired) // bianchi -> ritirato
  em.addStudentResult("gennaio", "verdi", ExamResult.succeeded(28)) // verdi -> 28
  em.addStudentResult("gennaio", "neri", ExamResult.succeededCumLaude) // neri -> 30L

  em.addStudentResult("febbraio", "rossi", ExamResult.failed) // etc..
  em.addStudentResult("febbraio", "bianchi", ExamResult.succeeded(20))
  em.addStudentResult("febbraio", "verdi", ExamResult.succeeded(30))

  em.addStudentResult("marzo", "rossi", ExamResult.succeeded(25))
  em.addStudentResult("marzo", "bianchi", ExamResult.succeeded(25))
  em.addStudentResult("marzo", "viola", ExamResult.failed)

  println(em.getAllStudentsFromCall("gennaio"))
  println(em.getAllStudentsFromCall("marzo"))

  println("evaluation map from call" + em.getEvaluationsMapFromCall("gennaio"))

  println(em.getResultsMapFromStudent("rossi"))
  println(em.getResultsMapFromStudent("bianchi"))
  println(em.getResultsMapFromStudent("neri"))

  println(em.getBestResultFromStudent("rossi"))
  println(em.getBestResultFromStudent("bianchi"))
  println(em.getBestResultFromStudent("neri"))
  println(em.getBestResultFromStudent("viola"))
  println(em.getBestResultFromStudent("marta"))

}