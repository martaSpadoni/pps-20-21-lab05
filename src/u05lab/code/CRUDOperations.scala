package u05lab.code

import u05lab.code.CRUDFunctions.CRUD

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object CRUDOperations extends App {
  /* Linear sequences: List, ListBuffer */
  var list = scala.List(10, 20, 30, 40, 50) //create op
  println("LIST")
  /*Read operations*/
  println("Size: "+ list.size, "First element: " + list.head,
    "Last element: "+ list.last,"3rd element: " + list(3))

  println("UPDATE OPERATIONS")
  list = 0 :: list
  println(list)
  list = list ::: scala.List(1, 2, 3, 4, 5)
  println(list)
  list = list :+ 6
  println(list)
  list = list.updated(2,10)
  println(list)

  println("REMOVE OPERATIONS")
  list = list.drop(1) //remove head
  println(list)
  list = list.dropRight(1) //remove tail
  println(list)
  list = list.diff(scala.List(10)) // remove just the first occurrence of 10
  println(list)
  //remove 5th element
  val rl = list.splitAt(5)
  list = rl._1 ::: rl._2.drop(1)
  println(list)

  println("LIST BUFFER")
  val listBuffer = ListBuffer(10, 20, 30, 40, 50) //create op

  /*Read operations*/
  println("Size: "+ listBuffer.size,"First element: " + listBuffer.head,
    "Last element: "+ listBuffer.last,"3rd element: " + listBuffer(3))

  println("UPDATE OPERATIONS")
  println(0 +=: listBuffer)
  println(listBuffer ++= ListBuffer(1, 2, 3, 4, 5))
  println(listBuffer += 6)
  listBuffer.update(2,10)
  println(listBuffer)

  println("REMOVE OPERATIONS")/*Remove operations*/
  listBuffer.remove(0) //remove head
  println(listBuffer)
  listBuffer.remove(listBuffer.size-1) //remove tail
  println(listBuffer)
  listBuffer -= 10 // remove just the first occurrence of 10
  println(listBuffer)
  listBuffer.remove(5)//remove 5th element
  println(listBuffer)

  /* Indexed sequences: Vector, Array, ArrayBuffer */
  var vector = Vector(10, 20, 30, 40, 50)
  println("VECTOR")
  /*Read operations*/
  println("Size: "+ vector.size,"First element: " + vector.head,
    "Last element: "+ vector.last,"3rd element: " + vector(3))

  println("UPDATE OPERATIONS")
  vector = 0+:vector
  println(vector)
  vector = vector++:Vector(1,2,3,4,5)
  println(vector)
  vector = vector:+6
  println(vector)
  vector = vector.updated(2,10)
  println(vector)

  println("REMOVE OPERATIONS")/*Remove operations*/
  vector = vector.drop(1) //remove head
  println(vector)
  vector = vector.dropRight(1) //remove tail
  println(vector)
  vector = vector.diff(Vector(10)) // remove just the first occurrence of 10
  println(vector)
  val vrl=vector.splitAt(5)//remove 5th element
  vector = vrl._1 ++: vrl._2.drop(1)
  println(vector)

  //array supports all methods of vectors used before
  var arr = Array(10, 20, 30, 40, 50)
  println("ARRAY")
  /*Read operations*/
  println("Size: "+ arr.size,"First element: " + arr.head,
    "Last element: "+ arr.last,"3rd element: " + arr(3))

  //arrayBuffer supports all methods of ListBuffer used before
  val arrBuf = ArrayBuffer(10, 20, 30, 40, 50)
  println("ARRAY BUFFER")
  /*Read operations*/
  println("Size: "+ arrBuf.size,"First element: " + arrBuf.head,
    "Last element: "+ arrBuf.last,"3rd element: " + arrBuf(3))
  println("UPDATE OPERATIONS")
  0 +=: arrBuf
  println(arrBuf)
  arrBuf ++= ListBuffer(1, 2, 3, 4, 5)
  println(arrBuf)
  arrBuf += 6
  println(arrBuf)
  arrBuf.update(2,10)
  println(arrBuf)

  println("REMOVE OPERATIONS")/*Remove operations*/
  arrBuf.remove(0) //remove head
  println(arrBuf)
  arrBuf.remove(arrBuf.size-1) //remove tail
  println(arrBuf)
  arrBuf -= 10 // remove just the first occurrence of 10
  println(arrBuf)
  arrBuf.remove(5)//remove 5th element
  println(arrBuf)

  /* Sets */
  var set = Set(10,20,30,40,50)
  println("SET")
  /*Read operations*/
  println("Size: "+ set.size)
  println("UPDATE OPERATIONS")
  set = set + 60
  println(set)
  set = set ++ Set(1,2,3,4,5)
  println(set)
  println("REMOVE OPERATIONS")
  set = set.drop(1) //remove one element
  println(set)
  set = set &~ Set(1,2,3)
  println(set)

  val mutableSet = collection.mutable.Set(10, 20, 30, 40, 50)
  println("MUTABLE SET")
  /*Read operations*/
  println("Size: "+ mutableSet.size)
  println("UPDATE OPERATIONS")
  mutableSet += 60
  println(mutableSet)
  mutableSet ++= Set(1,2,3,4,5)
  println(mutableSet)
  println("REMOVE OPERATIONS")
  mutableSet -= 10 //remove element 10
  println(mutableSet)
  mutableSet --= Set(1,2,3)
  println(mutableSet)

  /* Maps */

  var map = Map(10 -> "a", 20 -> "b", 30 -> "c", 40 -> "d")
  println("MAP")
  /*Read operations*/
  println(map(10))
  println("UPDATE OPERATIONS")
  map = map + (50 -> "e")
  println(map)
  map = map + (10 -> "update")
  println(map)
  println("REMOVE OPERATIONS")
  map = map.filterKeys(k => k != 20) //removes entry with 20 as key
  println(map)

  var mutableMap = collection.mutable.Map(10 -> "a", 20 -> "b", 30 -> "c", 40 -> "d")
  println("MUTABLE MAP")
  /*Read operations*/
  println(mutableMap(10))
  println("UPDATE OPERATIONS")
  mutableMap += (50 -> "e")
  println(mutableMap)
  mutableMap += (10 -> "update")
  println(mutableMap)
  println("REMOVE OPERATIONS")
  mutableMap -= 20 //removes entry with 20 as key
  println(mutableMap)

}
