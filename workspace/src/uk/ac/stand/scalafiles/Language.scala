package uk.ac.stand.scalafiles

import uk.ac.stand.minion.EssenceToMinion

class Language {
  
  type Id = String
  type Op = String
  val Env = new scala.collection.mutable.HashMap[Id, Var]
  
  trait Var {   
    
    def size() = 0
    def set(s : Array[String]): Var = {
      for(a <- s) print(a)
      println()
      //if(size() != s.length) throw new IllegalArgumentException("Parameter string is the wrong size: " + size() + " versus " + s.length)
      
      val n = seth(s)
     
      n //Pass back the variable
    }
    def seth(s : Array[String]): Var = seth(s,0)
    def seth(s: Array[String], i: Int): Var
  }
  case class EArray(v: Array[Var]) extends Var {
    override def size() = v.foldLeft(0){(a,b) => a+b.size+1}
    
    
    override def seth(s : Array[String], i: Int): Var =  {
      print("seth: ")
      for(a <- s) print(a)
      println()
      
      if(v.first.isInstanceOf[EArray] /*|| v.first.isInstanceOf[ETuple]*/){
	      
	      for(x <- 0 to v.length-1) {
	        println("x: " + x + " subarray " + v(x).size + " string: " + s.length + "  ***  v(x) = v(x).set(Array[String](s(x))) ")
	        v(x) = v(x).set(s.slice(x,v(x).size).force)
	      }	
      } else {
        for(x <- 0 to v.length-1) {
          v(x) = v(x).seth(s,x) //We're down to the variables themselves now rather than an array
        }
      }
      this
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
    
    override def seth(s : Array[String], i : Int): Var =  {
      println("EInt: " + this + " s: " + s.first + "i: " + i)
      EInt(EssenceToMinion.getIntValues(s.first).apply(i))
    }
  }
  case class EBool(v: java.lang.Boolean) extends Var {
    def this() = this(false)
    
    override def seth(s : Array[String], i : Int): Var =  {
      if (EssenceToMinion.getIntValues(s.first).apply(i).compareTo(0) > 1)
        EBool(true)
      else
        EBool(false)
    }
    }
  case class EDouble(v: java.lang.Double) extends Var {
    def this() = this(0.0)
    
    override def seth(s : Array[String], i: Int): Var =  {
      EDouble(EssenceToMinion.getDoubleValues(s.first).apply(i))
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
