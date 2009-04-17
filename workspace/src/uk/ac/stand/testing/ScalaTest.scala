package uk.ac.stand.testing

import uk.ac.stand.scalafiles.Stab._

object ScalaTest {
  def main(args : Array[String]) : Unit = {
    
    val x = Array[Value](EInt(1),EInt(2),EInt(3))
    val y = Array[Value](EArray(x), EArray(x))
    
    println(printMatrix(y, List(ERange(0,1), ERange(0,2))))
    
  }

}
