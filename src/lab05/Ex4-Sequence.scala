package lab05

import lab05.Function.sequence

object Function{

  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    val acc:Option[List[A]] = Some(List.empty)
    a.foldRight(acc)((e, a)=> e match {
      case Some(value) if a.isDefined => Some(value :: a.get)
      case _ => None
    })
  }
}

object SequenceTest extends App{
  val list:List[Option[Int]] = List(Some(1), Some(2), Some(3))
  val NoneList:List[Option[Int]] = List(Some(1), Some(2), None)
  println(sequence(list))
  println(sequence(NoneList))
}
