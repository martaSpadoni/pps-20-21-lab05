package u05lab.code

import u05lab.code.Function.sequence

object Function{

  def sequence[B](a: scala.List[Option[B]]): Option[scala.List[B]] = {
    val op:(Option[B], Option[scala.List[B]]) => Option[scala.List[B]] = (e, b)=> e match {
        case Some(value) if b.isDefined => Some(value :: b.get)
        case _ => None
      }
    val acc:Option[scala.List[B]] = Some(scala.List.empty)
    a.foldRight(acc)(op)
  }
}

object SequenceTest extends App{
  val list:scala.List[Option[Int]] = scala.List(Some(1), Some(2), Some(3))
  val NoneList:scala.List[Option[Int]] = scala.List(Some(1), None, None)
  println(sequence(list))
  println(sequence(NoneList))
}
