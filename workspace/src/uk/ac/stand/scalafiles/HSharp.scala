package uk.ac.stand.scalafiles;
object HSharp {
  
  var debug_on = false;
  
  def setdebug(b: Boolean) {
    debug_on = b
  }
  
  type Id = String
  type Op = String
  val Env = new scala.collection.mutable.HashMap[Id, Value]
  
  trait Term
  case class Stats(terms: List[Term]) extends Term
  
  case class Assign(n: Id, v: Exp) extends Term
  case class ElAssign(n: Id, e: Exp, v: Exp) extends Term
  case class VarDef(n: Id, e: Exp) extends Term
  case class While(t: Exp, s: Stats) extends Term
  
  trait Exp extends Term
  case class Var(i: Id) extends Exp
  case class TupleEl(n: Id, e: Exp) extends Exp
  case class EOp1(o: Op, e: Exp) extends Exp
  case class EOp(o: Op, l: Exp, r: Exp) extends Exp
  
  trait Value extends Exp
  case class ETuple(v: List[Exp]) extends Value
  case class EInt(v: Int) extends Value
  case class EBool(v: Boolean) extends Value
    
  def run(s: Stats): List[Any] = {
    
    if (debug_on) println("run: " + s.terms)
    
    try {
	    s.terms match {
	      case (_:Value) :: List() => List(s.terms.head)
	      case a :: List() => List(interpret(a)) //If there is only one class left interpret and return
	      case (a:Value) :: tail => run(Stats(tail)).::(a) //If head is a value eval the tail and leave the head to return
	      case _ => run(Stats(s.terms.filter(null!=).map(x => interpret(x)))) //Remove null values from the list
	    }
    } catch {
      case e: IllegalArgumentException => println(e);return List() //Any failures caught here and returned as empty to allow new calls to run to complete
      case _ => println("error");return List()
    }
  }
  
  def interpret(b: Term): Term = {
    if(neval(b)) return null else eval(b) //If it is a while loop replace with null, otherwise go to eval
  }
  
  //Evaluate but don't replace a value in the list of statements
  def neval(b: Term): Boolean = {
    if (debug_on) println("neval " +b)
    b match {
      case null => return true //Strip out nulls from the list
      
      case While(t, s) => {
        eval(t) match {
          case (EBool(true)) => {run(s);neval(While(t,s))}
          case (EBool(false)) => true
          case _ => throw new IllegalArgumentException("While test " + t + " does not evaluate to an EBool, instead " + eval(t))
        } 
      }
    
      case _ => false
    }
  }
  
  //Evaluate
  def eval(b: Term): Exp =  {
    if (debug_on) println("eval " + b)
    b match {      
    case Var(i) => {
      Env.get(i) match {
        case None => throw new IllegalArgumentException("Variable " + i + " was not found")
        case Some(x) => x
      }
    }
    
    case Assign(n,v:Value) => Env.put(n,v);v //Map the value to n and return
    case Assign(n,v) => eval(Assign(n,eval(v)))
    
    case ElAssign(n,EInt(i),v:Value) => {
      var l = Env.get(n)
      l match { //If the identifier isn't a list complain - Some is used to test for null values
        case None => throw new IllegalArgumentException("Variable " + n + " was not found")
        case Some(ETuple(a)) => if(boundsCheck(i,a)) Env.put(n,ETuple(a.splitAt(i)._1 ::: List(v) ::: a.splitAt(i)._2.tail)) //Split the list and insert at the relevant point
        case _ => throw new IllegalArgumentException("Variable " + n + " is not a tuple")
      }
      v //Return the value
    }
    case ElAssign(n,t,v) => eval(ElAssign(n,eval(t),eval(v)))
    
    case VarDef(n,e: Value) => Env.put(n,e);ETuple(List()) //Variable definition returns an empty tuple representing void
    case VarDef(n,e) => eval(VarDef(n,eval(e)))
    
    case (EInt(v)) => EInt(v)
    case (EBool(v)) => EBool(v)
    case (ETuple(v)) => ETuple(v)
    
    case EOp1("not", EBool(v)) => EBool(!v)
    case EOp1("-", EInt(v)) => EInt(-v)
    case EOp1("fst", ETuple(v)) => v.head
    case EOp1("snd", ETuple(v)) => v.tail.head
    case EOp1(o,v) => eval(EOp1(o, eval(v).asInstanceOf[Exp]))
    
    case EOp("+",EInt(l),EInt(r)) => EInt(l+r)
    case EOp("-",EInt(l),EInt(r)) => EInt(l-r)
    case EOp("*",EInt(l),EInt(r)) => EInt(l*r)
    case EOp("<",EInt(l),EInt(r)) => EBool(l<r)
    case EOp(">",EInt(l),EInt(r)) => EBool(l>r)
    case EOp("=",EInt(l),EInt(r)) => EBool(l==r)
    case EOp("=",EBool(l),EBool(r)) => EBool(l==r)
    case EOp("&&",EBool(l),EBool(r)) => EBool(l&&r)
    case EOp("||",EBool(l),EBool(r)) => EBool(l||r)
    case EOp(o,l,r) => eval(EOp(o,eval(l).asInstanceOf[Exp],eval(r).asInstanceOf[Exp]))
    
    case TupleEl(n,EInt(v)) => { //Return an element from a tuple
      Env.get(n) match {
        case None => throw new IllegalArgumentException("Variable " + n + " doens't exist")
        case Some(ETuple(a)) => boundsCheck(v,a); a.splitAt(v)._2.head //use the boundsCheck exception rather than the boundsCheck boolean return value to test
        case _ => throw new IllegalArgumentException("Variable " + n + " is not a tuple")
      }
    }
    case TupleEl(n,v) => eval(TupleEl(n,eval(v).asInstanceOf[Exp]))
    }
  } 
  
  //Checks whether a given array index will fail for more graceful handling - throws an exception and return booleans
  def boundsCheck(index: Int, array: List[Term]): Boolean = {
    if(index<0 || index > array.length) throw new IllegalArgumentException("Array index " + index + " is invalid"); false
    true
  }
  
}
