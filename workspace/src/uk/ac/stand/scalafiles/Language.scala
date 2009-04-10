package uk.ac.stand.scalafiles

import uk.ac.stand.minion.EssenceToMinion
import scala.collection.mutable.HashMap

class Language {
  
  type Id = String
  type Op = String
  val Env = new HashMap[Id, Var]
  
  trait Var {   
    
    def size() = 1
    def set(s : java.util.Iterator[String]): Var
    def getValue(): Any
  }
  case class EArray(v: Array[Var]) extends Var {
    override def size() = v.foldLeft(0){(a,b) => a+b.size+1}
    
    def getValue = v
    
    override def set(s : java.util.Iterator[String]): Var =  {
      
      if(v.first.isInstanceOf[EArray] /*|| v.first.isInstanceOf[ETuple]*/){
	      
	      for(x <- 0 to v.length-1) {
	        v(x) = v(x).set(s)
	      }	
      } else {
        
        for(x <- 0 to v.length-1) {
          v(x) = v(x).set(s) //We're down to the variables themselves now rather than an array
        }
      }
      this
    }
    
    override def toString(): String = {
      v.foldLeft(""){(a,b) => a+b.toString + " "} + "\n"
      
    }
  }
  /*
  case class ETuple(v: List[Var]) extends Var {
    override def size() = v.length
    override def seth(s : Array[String], i: Int): Var =  {
      var l : List[Var] = List[Var]()
      
      for(i <- 0 to v.length-1) {
        l ++ List[Var](v(i).set(Array[String](s(i)))) //Could use s.split(i,i).force instead, but this is more self-explanatory
      }
      
      ETuple(l)
    }
  }*/
  case class EInt(v: java.lang.Integer) extends Var {
    def this() = this(0)
    def getValue = v
    
    override def set(s : java.util.Iterator[String]): Var =  {
       EInt(Integer.parseInt(s.next))
    }
  }
  case class EBool(v: java.lang.Boolean) extends Var {
    def this() = this(false)
    def getValue = v
    
    override def set(s : java.util.Iterator[String]): Var =  {
      if (Integer.parseInt(s.next) > 1)
        EBool(true)
      else
        EBool(false)
    }
    }
  case class EDouble(v: java.lang.Double) extends Var {
    def this() = this(0.0)
    def getValue = v
    
    override def set(s : java.util.Iterator[String]): Var =  {
      EDouble(java.lang.Double.parseDouble(s.next))
    }
  }
  
  def createArray(dimensions: Array[int], t: Var): EArray = {
        
    val a = EArray(new Array[Var](dimensions.first))
    createArrayRec(a,dimensions.drop(1), t)
    
    return a
  }
  
  private def createArrayRec(a: EArray, d: Seq[Int], t: Var) {
    if(d.length==0) {
      for(x <- 0 to a.v.length-1) {
        a.v(x) = t
      }
    } else {
	    
	    for(x <- 0 to a.v.length-1) {
	      val y = new EArray(new Array[Var](d.first))
	      a.v(x) = y
	      createArrayRec(y,d.drop(1),t)
	    }
    }
  }
}
