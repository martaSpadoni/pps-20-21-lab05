package u05lab.code

import u05lab.code.ExamsManagerTest.Kind.{FAILED, RETIRED, SUCCEEDED}

object ExamsManagerTest extends App {

  trait Kind
  object Kind {
    case class RETIRED() extends Kind
    case class FAILED() extends Kind
    case class SUCCEEDED() extends Kind
  }

  case class ExamResult (val kind: Kind, val evaluation:Option[Int], val cumLaude:Boolean)

  object ExamResult{
    def failed: ExamResult = ExamResult(FAILED(), Option.empty, false)
    def retired: ExamResult = ExamResult(RETIRED(), Option.empty, false)
    def succeeded(evaluation: Int): ExamResult = ExamResult(SUCCEEDED(), Some(evaluation), false)
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





}