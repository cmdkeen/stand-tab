package uk.ac.stand.testing.junit

import uk.ac.stand.scalafiles.Stab._

class StabTest {

  def test_printMatrix(): String = {
    
    val x = Array[Value](EInt(1),EInt(2),EInt(3))
    val y = Array[Value](EArray(x), EArray(x))
    
    printMatrix(y, List(ERange(0,1), ERange(0,2)))
    
  }
  
  def test_param(a:List[Exp]): (String, String) = {
    emain.delete(0, emain.length)
    eparam.delete(0, eparam.length)
    param(a)
    
    (emain.toString, eparam.toString)
  }
  
  def test_param1m():String = {
    test_param(List[Exp](Var("test"), EInt(3)))._1
  }
  
  def test_param2m():String = {
    test_param(List[Exp](Var("test"), EBool(true)))._1
  }
  
  def test_param1p():String = {
    test_param(List[Exp](Var("test"), EInt(3)))._2
  }
  
  def test_param2p():String = {
    test_param(List[Exp](Var("test"), EBool(true)))._2
  }
  
  def test_param3p():String = {
    test_param(List[Exp](Var("test"), EBool(false)))._2
  }
  
}
