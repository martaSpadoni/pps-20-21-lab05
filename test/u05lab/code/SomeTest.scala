package u05lab.code

import org.junit.jupiter.api.Assertions.{assertEquals, assertThrows}
import org.junit.jupiter.api.Test

class SomeTest {

  @Test
  def testZipRight(): Unit ={
    val l = List("a","b","c")
    assertEquals(List.nil, List.nil.zipRight)
    assertEquals(List(("a",0), ("b",1), ("c",2)), l.zipRight)
  }

  @Test
  def testPartition(): Unit ={
    val l = List(10, 11, 12, 13, 14)
    assertEquals((List(10, 12, 14), List(11,13)), l.partition(i => i % 2 == 0))
  }

  @Test
  def testSpan(): Unit ={
    val l = List(10, 20, 30 , 40, 50)
    assertEquals((List(10, 20, 30), List(40, 50)), l.span(e => e <= 30))
  }

  @Test
  def testReduceOnEmptyList(): Unit ={
    assertThrows(classOf[UnsupportedOperationException], () => List.nil.reduce((a:Int, _:Int) => a))
  }

  @Test
  def testReduce(): Unit ={
    val l = List(10, 20, 30, 40)
    assertEquals(100, l.reduce((a, b) => a+b))
  }

  @Test
  def testReduceOnOneElementList(): Unit ={
    val l = List(10)
    assertEquals(10, l.reduce((a, b) => a+b))
  }

  @Test
  def testTakeRight(): Unit ={
    val l = List("a", "b", "c", "d", "e")
    assertEquals(List("d", "e"), l.takeRight(2))
  }

  @Test
  def testCollect(): Unit ={
    val l = List(1, 2, 3, 4, 5, 6, 7)
    val isEven: PartialFunction[Int, String] = {
      case x if x % 2 == 0 => x+" is even"
    }
    assertEquals(List("2 is even","4 is even","6 is even"), l.collect(isEven))
  }

}