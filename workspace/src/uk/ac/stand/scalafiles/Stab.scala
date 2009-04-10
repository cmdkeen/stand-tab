package uk.ac.stand.scalafiles

import scala.collection.jcl.Conversions._
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

object Stab {
  
  type Id = String
  type Op = String
  
  var debug_on = false;
  
  def setdebug(b: Boolean) {
    debug_on = b
  }
  
  
  val Env = new HashMap[Id, Value]
  val Functions = new HashMap[Id, FuncDef]
  val emain = new StringBuffer
  val eparam = new StringBuffer
  
  var localMemory:Map[Id, Value] = new HashMap[Id, Value]
  
  case class Program(funcs:List[FuncDef], stats:List[Stats]) {
    def this(f: java.util.List[FuncDef], s: java.util.List[Stats]) = this (f.toList, s.toList)
  }
  
  case class Stats(terms: List[Term]) {
    def this(t: java.util.List[Term]) = this(t.toList)
  }
  
  trait Term
  case class Assign(n:Id, o:Op, v: Exp) extends Term
  case class ElAssign(n: Id, e: Exp, o:Op, v: Exp) extends Term
  case class VarDef(n: Id, e: Exp) extends Term
  case class While(t: Exp, s: Stats) extends Term
  case class For(v: Id, c: Exp, s : Stats) extends Term //Variable, Collection to iterate over, Statements
  case class If(i: Exp, t: Stats, e: Option[Stats]) extends Term {
    def this(i: Exp, t: Stats, e: Stats) = this (i,t,Some(e))
    def this(i: Exp, t: Stats) = this(i,t,None)
  }
  case class Break() extends Term
  case class Continue() extends Term
  case class Return(v: Option[Exp]) extends Term {
    def this() = this(None)
  }
  case class FuncDef(n:Id, a: List[Id], s:Stats) extends Term {
    def this(n:Id, ar: java.util.List[Id], s:Stats) = this(n, ar.toList, s)
  }
  
  trait Exp extends Term
  case class Var(i: Id) extends Exp
  case class ArrayEl(n: Id, e: Exp) extends Exp
  case class EOp1(o: Op, e: Exp) extends Exp
  case class EOp(o: Op, l: Exp, r: Exp) extends Exp
  case class Call(n:Id, a: List[Exp]) extends Exp {
    def this(n:Id, ar: java.util.List[Exp]) = this(n, ar.toList)
  }
  case class Method(n:Id, m:Id, a: List[Exp]) extends Exp {
    def this(n:Id, m:Id, ar: java.util.List[Exp]) = this(n,m, ar.toList)
  }
  case class EString(s:String) extends Exp
  
  trait Value extends Exp
  case class EArray(v: Array[Value]) extends Value {
    def this(l: java.util.List[Value]) = this(l.toList.toArray)
  }
  case class EInt(v: Int) extends Value
  case class EBool(v: Boolean) extends Value
  
  def runFunction(name: Id, args: List[Exp]): Any = {
    val local = new HashMap[Id, Value]
    val func: FuncDef = Functions(name)
    for(x <- args; y <- func.a) eval(x) match {
      case z:Value => local += y -> z
      case _ => throw new IllegalArgumentException("While test " + x + " does not evaluate to an EBool, instead " + eval(x))
    }
    
    run(func.s, local)
  }
 
  def runProgram(p: Program): (String, String) = {    
    for(x <- p.funcs) Functions += x.n -> x //Map function name to function
    
    //Empty the output buffers ready for the new interpretation
    emain.delete(0, emain.length)
    eparam.delete(0, eparam.length)
    
    for(x <- p.stats) run(x)
    
    if(debug_on) println(emain.toString + "\n\n" + emain.toString)
    
    (emain.toString, eparam.toString)//Return the essence files now they are assembled
  }
  
  def run(s:Stats): List[Any] = {
    run(s, new HashMap[Id, Value]())  
  }
  
  def run(s: Stats, local:Map[Id, Value]): List[Any] = {
    localMemory = local
    
    if (debug_on) println("run: " + s.terms)
    
    try {
	    s.terms match {
	      case Nil => return List() //List is empty - nothing to evaluate
	      case (_:Value) :: List() => List(s.terms.head)
          case Return(None) :: _ => List()
          case Return(Some(v:Value)) :: _ => List(v)
          case Return(Some(e:Exp)) :: _ => List(interpret(e))
          case (c:Continue) :: _ => List(c)
          case (b:Break) :: _ => List(b)
	      case a :: List() => List(interpret(a)) //If there is only one class left interpret and return
	      case (a:Value) :: tail => run(Stats(tail), local).::(a) //If head is a value eval the tail and leave the head to return
	      case _ => run(Stats(s.terms.filter(null!=).map(x => interpret(x))), local) //Remove null values from the list
	    }
    } catch {
      case e: IllegalArgumentException => println(e);return List() //Any failures caught here and returned as empty to allow new calls to run to complete
      case _ => println("error");return List()
    }
  }
  
  def interpret(b: Term): Term = {
    neval(b) match {
      case (true, None) => return null
      case (true, Some(r)) => return r
      case (false, _) => eval(b) //If it is a while loop replace with null, otherwise go to eval
    }
  }
  
  //Evaluate but don't replace a value in the list of statements
  def neval(b: Term): (Boolean, Option[Return]) = {
    if (debug_on) println("neval " +b)
    b match {
      case null => return (true, None) //Strip out nulls from the list
      
      case While(t, s) => {
        val x = eval(t)
        return x match {
          case EBool(true) => {
            run(s, localMemory).head match {
              case r:Return => (true, Some(r))
              //Don't need to deal with continue as execution of that loop already stopped
              case b:Break => (true, None)
              case _ => neval(While(t,s))
            }
            
          }
          case EBool(false) => (true, None)
          case _ => throw new IllegalArgumentException("While test " + t + " does not evaluate to an EBool, instead " + x)
        } 
      }
      
      //For is supposed to be of the type for(x:EArray) stats
      case For(v: Id, c: Exp, s : Stats) => {
        var a = interpret(c)
        var i:Array[Value] = null
        a match {
          case EArray(l) => i = l
        }
          //TODO allow iteration over other things - iterpret needs to return some other form of Iterable
        if(i==null || ! i.isInstanceOf[Iterable[Value]]) throw new IllegalArgumentException("For loop requires iterable to loop over, " + c + " is not iterable")
          
        for(x <- i) {
          localMemory += v -> x
          run(s, localMemory).head match {
            case r:Return => (true, Some(r))
            //Don't need to deal with continue as execution of that loop already stopped
            case b:Break => (true, None)
          }
        }
        localMemory - v  //Remove the iterating variable
      }
      
      case If(i: Exp, t: Stats, e) => {
        val x = eval(i)
        return x match {
          case EBool(true) => {
            run(t, localMemory).head match {
              case r:Return => (true, Some(r))
              case _ => (true, None)
            }
          }
          case EBool(false) => {
            e match {
              case None => (true, None)
              case Some(es) => run(es, localMemory).head match {
                case r:Return => (true, Some(r))
                case _ => (true, None)
              }
            }
          }
          case _ => throw new IllegalArgumentException("If test " + i + " does not evaluate to an EBool, instead " + x)
        }
      }

      case Assign(n, o:Op, v:Value) => { //If the variable is located in localMemory then update that, if not then the global
        if(localMemory.contains(n)) {
          localMemory += n -> augassign(o,localMemory.get(n),v) //augassign determines what the new value should be
        } else {
          Env += n -> augassign(o,Env.get(n),v); //Map the value to n and return
        }
      }
      
      case Assign(n, o, v) => neval(Assign(n,o, eval(v)))
	    
	  case ElAssign(n, EInt(i), o:Op, v:Value) => {
	    var l = Env.get(n)
	    l match { //If the identifier isn't a list complain - Some is used to test for null values
	      case None => throw new IllegalArgumentException("Variable " + n + " was not found")
	      case Some(EArray(a)) => if(boundsCheck(i,a.length)) {
	        if(localMemory.contains(n)) {
	          //localMemory += n -> EArray(a.splitAt(i)._1 ::: List(augassign(o,localMemory.get(n),v)) ::: a.splitAt(i)._2.tail) //Split the list and insert at the relevant point
	          a(i) = augassign(o,localMemory.get(n),v)
	          localMemory += n -> EArray(a)
	        } else {
	          //Env += n -> EArray(a.splitAt(i)._1 ::: List(augassign(o,Env.get(n),v)) ::: a.splitAt(i)._2.tail) //Split the list and insert at the relevant point
	          a(i) = augassign(o,Env.get(n),v)
	          Env += n -> EArray(a)
	        }
	      }
	      case _ => throw new IllegalArgumentException("Variable " + n + " is not a tuple")
	    }
	  }
	  
      case ElAssign(n,t,o,v) => neval(ElAssign(n,eval(t),o,eval(v)))
     
      case VarDef(n,e: Value) => Env.put(n,e);//Variable definition
      case VarDef(n,e) => neval(VarDef(n,eval(e)))
      
      case EString(s) => {
        emain.append(s).append("\n") //Add Essence' code to the output string
        if(eparam.length==0) if(s.startsWith("language",0))eparam.append(s).append("\n") //If nothing added then if this is the language def add it to the param file too
      }
      
	  case _ => return (false, None)
	}

    (true, None) //Did find something matching so don't proceed to eval in interpret
  }
  
  private def augassign(o:Op, l:Option[Value], r:Value): EInt = {
    
    var left:EInt = l match {
      case null => EInt(0)
      case None => EInt(0)
      case Some(EBool(true)) => EInt(1)
      case Some(EBool(false)) => EInt(0)
      case Some(v:EInt) => v
      case Some(v:Value) => throw new IllegalArgumentException("Passed left value wasn't and Integer or Boolean: found " + v) 
    }
    
    var right:EInt = r match {
      case null => EInt(0)
      case EBool(true) => EInt(1)
      case EBool(false) => EInt(0)
    }
    
    o match {
      case "=" => right
      case "+=" => EInt(left.v + right.v)
      case "-=" => EInt(left.v - right.v)
      case "*=" => EInt(left.v * right.v)
      case "/=" => if(right.v!=0) EInt(left.v / right.v) else throw new IllegalArgumentException("Right hand side of " + left + o + right + " is 0")
      case "%=" => EInt(left.v % right.v)
    }

  }
  
  //Evaluate
  def eval(b: Term): Exp =  {
    
    if (debug_on) println("eval " + b)
    b match {
	    case Var(n) => { //Return a variable
	      if(localMemory.contains(n)) {
	        localMemory(n) //Know localMemory contains n
	      } else {
	        Env.get(n) match {
	          case None => throw new IllegalArgumentException("Variable " + n + " was not found")
	          case Some(x) => x
	        }
	      }
	    }
     
        case ArrayEl(n, EInt(v)) => { //Return an element from a tuple
          if(localMemory.contains(n)) {
            localMemory(n) //Local memory definately contains n so no need to check
          } else {
            Env.get(n) match {
              case None => throw new IllegalArgumentException("Variable " + n + " doens't exist")
              //case Some(EArray(a)) => boundsCheck(v,a.length); a.splitAt(v)._2.head //use the boundsCheck exception rather than the boundsCheck boolean return value to test
              case Some(EArray(a)) => boundsCheck(v,a.length); a(v) //use the boundsCheck exception rather than the boundsCheck boolean return value to test
              case _ => throw new IllegalArgumentException("Variable " + n + " is not a tuple")
            }
          }
	    }
	    case ArrayEl(n,v) => eval(ArrayEl(n,eval(v).asInstanceOf[Exp]))
	    
	    //Deal with brackets
	    case (EInt(v)) => EInt(v)
	    case (EBool(v)) => EBool(v)
	    case (EArray(v)) => EArray(v)

     //1 arguments operators
	    case EOp1("not", EBool(v)) => EBool(!v)
	    case EOp1("-", EInt(v)) => EInt(-v)
	    case EOp1(o,v) => eval(EOp1(o, eval(v).asInstanceOf[Exp]))
	    
     //2 argument operators
	    case EOp("+",EInt(l),EInt(r)) => EInt(l+r)
	    case EOp("-",EInt(l),EInt(r)) => EInt(l-r)
	    case EOp("*",EInt(l),EInt(r)) => EInt(l*r)
	    case EOp("/",EInt(l),EInt(r)) => if(r!=0) EInt(l/r) else throw new IllegalArgumentException("Right hand side of " + l + "/" + r + " is 0")
     	case EOp("^",EInt(l),EInt(r)) => EInt(l^r) //Power
	    case EOp("<",EInt(l),EInt(r)) => EBool(l<r)
	    case EOp(">",EInt(l),EInt(r)) => EBool(l>r)
	    case EOp("==",EInt(l),EInt(r)) => EBool(l==r)
	    case EOp("==",EBool(l),EBool(r)) => EBool(l==r)
	    case EOp("and",EBool(l),EBool(r)) => EBool(l&&r)
	    case EOp("or",EBool(l),EBool(r)) => EBool(l||r)
	    case EOp(o,l,r) => eval(EOp(o,eval(l).asInstanceOf[Exp],eval(r).asInstanceOf[Exp]))
     
     //Function things
     //Builtin functions
     case Call("param", a) => {
       
       if(a.length!=2) throw new IllegalArgumentException("Function param requires 2 arguments not " + a.length + " : " + a)
       
       a.head match {
         case Var(v) => eval(a.tail.head) match {
           case EInt(x) => emain.append("given " + v + " : Int\n"); eparam.append("parameter " + v + " is " + x + "\n")
           case EBool(true) => emain.append("given " + v + " : Int\n"); eparam.append("parameter " + v + " is 1\n")
           case EBool(false) => emain.append("given " + v + " : Int\n"); eparam.append("parameter " + v + " is 0\n")
           case EArray(l) => {
             val z = l.map(y => y match {
               case EInt(x) => x
               case EBool(true) => 1
               case EBool(false) => 0
             })
             //Left fold (type of list traversal) on value list to find the highest and lowest values
             //TODO impement better way of doing this
             val min = (z(0) /: z.slice(1,z.length)) ((a,b)=> if(a<b) a else b)
             val max = (z(0) /: z.slice(1,z.length)) ((a,b)=> if(a>b) a else b)
             emain.append("given " + v + " : matrix indexed by [ int(1.." + l.length + ") ] of int( + " + min + ".." + max + ")\n"); 
             eparam.append("parameter " + v + " is [")
             val zs = z.map(String.valueOf(_))
             eparam.append((zs(0) /: zs.slice(1,zs.length)) (_ + ", " + _)).append("]")
             
           }
         }
         case v => throw new IllegalArgumentException("Function param requires first argument to be a Variable not " + v)
       }
       
       null //Doesn't return anything
     }
     
     case Call("freqArray", a) => {
       //TODO take a range and discard outwith
       
       if(a.length!=1) throw new IllegalArgumentException("Function freqArray requires 1 argument1 not " + a.length + " : " + a)
       
       eval(a.head) match {
         case EArray(l) => {
           val x = l.map(y => y match {
               case EInt(x) => x
               case EBool(true) => 1
               case EBool(false) => 0
             })
           val max = (x(0) /: x.slice(1,x.length)) ((a,b)=> if(a>b) a else b)
           
           val ret = new Array[Int](max)
           
           for(z <- x) ret(z) += 1
           
           return EArray(ret.map(y=> EInt(y))) //Return as an EArray of EInts
         }
         case x => throw new IllegalArgumentException("Function freqArray requires an array not " + x)
       }
       
       null
     }
     
     //Defined within code
     case Call(n:Id, a) => {
       
       if(!Functions.contains(n)) throw new IllegalArgumentException("Function " + n + " has not been defined")
       
       var f = Functions(n)
       
       if(f.a.length != a.length) throw new IllegalArgumentException("Function " + n + " takes " + f.a.length + " arguments not the " + a.length + " supplied")
       
       var lm = localMemory.clone
       
       for(x <- f.a; y <- a) y match {
         case z:Value => lm += x -> z
         case z => throw new IllegalArgumentException("For loop: Argument " + x + " is not a value : " + z)
       }
       
       run(f.s, lm).head match {
         case e:Exp => e
         case _ => null
       }
       
     }
     
     
     
	    
	    
	 //Method things
	    //case EOp1("fst", EArray(v)) => v.head
	    //case EOp1("snd", EArray(v)) => v.tail.head
     
     
    }
    
  } 
  
  //Checks whether a given array index will fail for more graceful handling - throws an exception and return booleans
  def boundsCheck(index: Int, length: Int): Boolean = {
    if(index<0 || index > length) throw new IllegalArgumentException("Array index " + index + " is invalid"); false
    true
  }
  
}


