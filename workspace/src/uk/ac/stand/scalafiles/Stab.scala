package uk.ac.stand.scalafiles

import scala.collection.jcl.Conversions._
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.lang.reflect.Field
import uk.ac.stand.impl._

object Stab {
  
  type Id = String
  type Op = String
  
  var debug_on = false;
  
  def setdebug(b: Boolean) {
    debug_on = b
  }
  
  case class Program(funcs:List[FuncDef], stats:List[Stats]) {
    def this(f: java.util.List[FuncDef], s: java.util.List[Stats]) = this (f.toList, s.toList)
    def statString(): String = {stats.toString} 
  }
  
  case class Stats(terms: List[Term]) {
    def this(t: java.util.List[Term]) = this(t.toList)
  }
  
  trait Term
  case class Assign(n:Id, o:Op, v: Exp) extends Term
  case class ElAssign(n: Id, e: Exp, o:Op, v: Exp) extends Term
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
  case class ArrayEl(n: Id, e: List[Exp]) extends Exp {//Can either be a single values or an EArray of values for multidimensional arrays
    def this(n:Id, el: java.util.List[Exp]) = this(n, el.toList)
  }
  case class EOp1(o: Op, e: Exp) extends Exp
  case class EOp(o: Op, l: Exp, r: Exp) extends Exp
  case class Call(n:Id, a: List[Exp]) extends Exp {
    def this(n:Id, ar: java.util.List[Exp]) = this(n, ar.toList)
  }
  case class Method(n:Exp, m:Id, a: List[Exp]) extends Exp {
    def this(o:Exp, m:Id, ar: java.util.List[Exp]) = this(o,m, ar.toList)
  }
  
  trait Value extends Exp
  case class EArray(v: Array[Value]) extends Value {
    def this(l: java.util.List[Value]) = this(l.toList.toArray)
   }
  case class EInt(v: Int) extends Value
  case class EBool(v: Boolean) extends Value
  case class EString(s:String) extends Value
  //Special classes for holding objects, created by the language
  //Things like teams will be loaded into global memory as an EArray of EObject(t)
  case class EObject(o: Any) extends Value
  //For holding range data such as domains - created by the range function
  case class ERange(l:Int, u:Int) extends Value {
    def check(v:Int): Boolean = { //Checks v is within the bounds (inclusive)
      v>=l && v<=u
    }
    def domain(): String = {
      "int(" + l + ".." + u + ")"
    }
  }
  
  
  val Env = new HashMap[Id, Value]
  val Functions = new HashMap[Id, FuncDef]
  
  val settings = new HashMap[Id, (java.lang.Class[_], java.lang.Object)]
  val teamFields = new HashMap[Id, (java.lang.Class[_], java.lang.Object)]
  val speakerFields = new HashMap[Id, (java.lang.Class[_], java.lang.Object)]
  
  val validSettingsFuncs = new HashMap[Id, Stats]
  val validTeamFieldsFuncs = new HashMap[Id, Stats]
  val validSpeakerFieldsFuncs = new HashMap[Id, Stats]
  var roomValidiationFunc:Option[Stats] = None
  
  val emain = new StringBuffer
  val eparam = new StringBuffer
  
  var localMemory:Map[Id, Value] = new HashMap[Id, Value]
  
  def getSettings(): java.util.Map[Flag, java.lang.Object] = {
    val l = new java.util.HashMap[Flag, java.lang.Object]
    
    //settings.foreach(x => l add (new Flag(x._1.toString, x._2._1),x._2._1))
    settings.keys.foreach(x => { //For some reason doesn't like above so do more verbosely
      val n:(Flag, java.lang.Object) = (new Flag(x,settings(x)._1), settings(x)._2)
      l += n._1 -> n._2
    })
    
    l  
  }
  
  def getTeamFields(): java.util.Map[Flag, java.lang.Object] = {
    val l = new java.util.HashMap[Flag, java.lang.Object]
    
    teamFields.keys.foreach(x => { //For some reason doesn't like above so do more verbosely
      val n:(Flag, java.lang.Object) = (new Flag(x,teamFields(x)._1), teamFields(x)._2)
      l += n._1 -> n._2
    })
    
    l  
  }
  
  def getSpeakerFields(): java.util.Map[Flag, java.lang.Object] = {
    val l = new java.util.HashMap[Flag, java.lang.Object]
    
    speakerFields.keys.foreach(x => { //For some reason doesn't like above so do more verbosely
      val n:(Flag, java.lang.Object) = (new Flag(x,speakerFields(x)._1), speakerFields(x)._2)
      l += n._1 -> n._2
    })
    
    l  
  }
  
  def validateSetting(name: String, v:java.lang.Object): Boolean = {
    var ret = true
    val value = convert(v)
    
    if(validSettingsFuncs.contains(name)) ret = runValidateFunction(validSettingsFuncs(name), List(("Value", value)))
    
    ret
  }
  
  def validateTeamField(name:String, t:Team, v:java.lang.Object):Boolean = {
    var ret = true
    val value = convert(v)
    
    if(validTeamFieldsFuncs.contains(name)) ret = runValidateFunction(validTeamFieldsFuncs(name), List(("Value", value), ("Team",EObject(t))))
    
    ret
  }
  
  def validateSpeakerField(name:String, s:Speaker, v:java.lang.Object):Boolean = {
    var ret = true
    val value = convert(v)
    
    if(validSpeakerFieldsFuncs.contains(name)) ret = runValidateFunction(validSpeakerFieldsFuncs(name), List(("Value", value), ("Speaker",EObject(s))))
    
    ret
  }
  
  def validateResults(name:String, v:Array[Array[java.lang.Object]]):Boolean = {
    var ret = true
    val value = convert(v)
    
    ret = roomValidiationFunc match {
      case Some(stats) => runValidateFunction(stats, List(("Results", value)))
      case None => true
    }
    
    ret
  }
  
  private def runValidateFunction(s:Stats, lm:List[(Id, Value)]): Boolean = {
    Env.clear
    localMemory.clear
    
    setupMemory
    
    val local = new HashMap[Id, Value]
  
    lm.foreach(x => local += x._1 -> x._2)
    
    run(s, local).head match {
      case EBool(o) => o
      case x => throw new Exception("Validation failed as code returned " + x)
    }
  }
  
  def setupMemory() {
    Env.clear
    localMemory.clear
    
    //Empty the output buffers ready for the new interpretation
    emain.delete(0, emain.length)
    eparam.delete(0, eparam.length)
    
    settings.clear
    teamFields.clear
    speakerFields.clear
    
    validSettingsFuncs.clear
    validSpeakerFieldsFuncs.clear
    validTeamFieldsFuncs.clear
    
    val s = uk.ac.stand.impl.Settings.getInstance
    if(s.getFlags!=null && s.getFlags.getFields!=null) s.getFlags.getFields.foreach(x => Env += x.getName -> convert(s.getFlagValue(x)))
    
    val c = uk.ac.stand.impl.Competition.getInstance
    if(c.getTeams!=null) Env += "teams" -> EArray(c.getTeams.map(x=> EObject(x)).toArray) //Map each team into an array and store it - allows for consistent ordering
  }
  
  def runInitial(p: Program) { //Use to extract the settings and setup the validation function
    setupMemory
    
    for(x<-p.stats) run(x)
  }
 
  def runDraw(p: Program, rounds: java.lang.Integer): java.util.ArrayList[String] = {//Creates Essence' string for translation by Tailor 
    Functions.clear
    for(x <- p.funcs) Functions += x.n -> x //Map function name to function
    
    setupMemory
    Env+= "round" -> EInt(rounds.intValue)
    
    for(x <- p.stats) run(x)
    
    if(debug_on) println(emain.toString + "\n\n" + emain.toString)
    
    //Return the essence files now they are assembled
    val ret = new java.util.ArrayList[String]
    ret add emain.toString
    ret add eparam.toString
    
    ret
  }
  
  def run(s:Stats): List[Any] = {
    run(s, new HashMap[Id, Value]())  
  }
  
  def run(s: Stats, local:Map[Id, Value]): List[Any] = {
    localMemory = local
    
    if (debug_on) println("run: " + s.terms)

	    s.terms match {
	      case Nil => return List() //List is empty - nothing to evaluate
	      //case (_:Value) :: List() => List(s.terms.head) //EString is a value so still need to interpret
          case Return(None) :: _ => List()
          case Return(Some(v:Value)) :: _ => List(v)
          case Return(Some(e:Exp)) :: _ => List(interpret(e))
          case (c:Continue) :: _ => List(c)
          case (b:Break) :: _ => List(b)
	      case a :: List() => List(interpret(a)) //If there is only one class left interpret and return
	      case (a:Value) :: tail => run(Stats(tail), local).::(interpret(a)) //If head is a value eval the tail and leave the head to return
	      case _ => run(Stats(s.terms.filter(null!=).map(x => interpret(x))), local) //Remove null values from the list
	    }
    /*} catch {
      case e: IllegalArgumentException => println(e);return List() //Any failures caught here and returned as empty to allow new calls to run to complete
      case _ => println("error");return List()
    }*/
  }
  
  def interpret(b: Term): Term = {
    if (debug_on) println("interp: " + b)
    
    b match {
      case r:Return => return r
      case c:Continue => return c
      case b:Break => return b
      case _ =>
    }
    
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
      
      //n:Id, a: List[Id], s:Stats
      case FuncDef(name,args,stats) => {
        /*
validateSetting(name, value)
validateTeamField(name, team, value)
validateSpeakerField(name, speaker, value)
validateRoomResult(array[tpr] of ints)
          */
        
        name match {
          case "validateSetting" => 
            //Only care about the first argument - the name of the field to validate
            addValidateSetting(args.head,stats)
            return (true, None)
          case "validateTeamField" =>
            addValidateTeamField(args.head, stats)
            return (true, None)
          case "validateSpeakerField" =>
            addValidateSpeakerField(args.head, stats)
            return (true, None)
          case "validateRoomResult" =>
            setValidateResult(stats)
          case _ => return(true, None) //Other functions definitions can be ignored as they are already in the function map after parsing
        }
        
      }
      
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
          case ERange(l,u) => {
            i = (l to u).map(x => EInt(x)).toArray 
          }
        }

        if(i==null) throw new IllegalArgumentException("For loop requires iterable to loop over, " + c + " is not iterable")
        
        for(x <- i) {
          localMemory += v -> x

          run(s, localMemory).head match {
            case r:Return => (true, Some(r))
            //Don't need to deal with continue as execution of that loop already stopped
            case b:Break => (true, None)
            case _ =>
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
          
      case EString(s) => {
        emain.append(s).append("\n") //Add Essence' code to the output string
        if(eparam.length==0) if(s.startsWith("language",0))eparam.append(s).append("\n") //If nothing added then if this is the language def add it to the param file too
      }
      
	  case _ => return (false, None)
	}

    (true, None) //Did find something matching so don't proceed to eval in interpret
  }
  
  private def augassign(o:Op, l:Option[Value], r:Value): Value = {
    
    o match {
      case "=" => return r
      case _ =>
    }
    
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
     
	     case ArrayEl(n, l) => {
	       var array:EArray = null
	       
	       if(localMemory.contains(n)) {
	            array = localMemory(n) match { //Local memory definately contains n so no need to check for None, just that is an array
	              case a:EArray => a
	              case _ => throw new IllegalArgumentException("Variable " + n + " is not an array")
	            }  
	          } else {
	            Env.get(n) match {
	              case None => throw new IllegalArgumentException("Variable " + n + " doens't exist")
	              case Some(a:EArray) => array = a
	              case _ => throw new IllegalArgumentException("Variable " + n + " is not an array")
	            }
	          }
	       if(array==null) throw new IllegalArgumentException("Variable " + n + " is not an array")
	       //array now contains an EArray
        
	       var el = l.map(y => eval(y)) //Now can check el to see if everything has been evaluated to a value
	       
	       var sub:EArray = array
	       var ret:Value = null
	       
	       for(x <- el)  {
	         if(sub==null) throw new IllegalArgumentException("Too many indices " + el.length + " for " + array) 
	         val y = x match { //Index into the next array
	           case EInt(v) => v
	           case EBool(true) => 1
	           case EBool(false) => 1
	           case _ => throw new IllegalArgumentException("Array element argument " + x + " does not evaluate to an index")
	         }
	         
	         if(y>=sub.v.length) throw new ArrayIndexOutOfBoundsException(y + " in " + sub.v.toString)
	         
	         sub.v(y) match {
	           case a:EArray => sub = a; false
	           case v => sub = null; ret = v
	         }
	       }
	       
	       ret
	     }
	    
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
     
     //Function calls

     case Call(n, a) => {
       
       //Builtin functions
       n match {
         case "param" => {val x = param(a); Env += x._1 -> x._2; return null;}
         case "paramMatrix" => {val x = paramMatrix(a); Env += x._1 -> x._2; return null;}
         case "range" => 
           if(a.length==1) {
             return range(EInt(0)::a)
           } else {
             return range(a) //Range can take a single argument range(x) => range(0,x)
           } 
         case "array" => return array(a)
         case "frequencyArray" => return freqArray(a)
         case "createArray" => return createArray(a)
         case "pastPositions" => return pastPositions()
         
         /*
createSetting(name, type, default value)
createTeamField(name, type, default value)
createSpeakerField(name, type, default value)
          */
         case "createSetting" =>
           createSetting(a)
           return null
         case "createTeamField" =>
           createTeamField(a)
           return null
         case "createSpeakerField" =>
           createSpeakerField(a)
           return null
         case _ => //not a builtin function, checks continue below
       }
       
       //Script defined function
       if(!Functions.contains(n)) throw new IllegalArgumentException("Function " + n + " has not been defined")
       
       var f = Functions(n)
       
       if(f.a.length != a.length) throw new IllegalArgumentException("Function " + n + " takes " + f.a.length + " arguments not the " + a.length + " supplied")
       
       var lm = localMemory.clone
       
       for(x <- f.a; y <- a) eval(y) match {
         case z:Value => lm += x -> z
         case z => throw new IllegalArgumentException("Function call arguments: Argument " + x + " is not a value : " + z)
       }
       
       run(f.s, lm) match {
         case (e:Exp) :: _ => e
         case x => 
           null
       }
       
     }

	 //Method calls
     //Method(n:Id, m:Id, a: List[Exp])
      
     case Method(ob,meth,args) => {
       
       val obj = ob match {
         case Var(x) => 
            if(localMemory.contains(x)) localMemory(x) else Env.get(x) match { //Find what name refers to in memory
              case Some(x) => x
              case None => throw new IllegalArgumentException("Method invocation on object that doesn't exist: " + x)
            }
         case x:ArrayEl => eval(x) 
       }
       
       meth match {
         case "fst" => obj match {
           case EArray(l) => if(l.length>0) l(0) else throw new ArrayIndexOutOfBoundsException("Operation fst on " + l + " failed")
           case _ => throw new IllegalArgumentException("Operation fst on " + obj + " is not defined - must be EArray")
         }
         case "snd" => obj match {
           case EArray(l) => if(l.length>1) l(1) else throw new ArrayIndexOutOfBoundsException("Operation snd on " + l + " failed")
           case _ => throw new IllegalArgumentException("Operation fst on " + obj + " is not defined - must be EArray")
         }
         case "size" => obj match {
           case EArray(l) => EInt(l.length)
           case _ => throw new IllegalArgumentException("Operation size on " + obj + " is not defined - must be EArray")
         }
         case "indicies" => obj match { //Assume each sub array is of the same size as others - ie array[1][2].length == array[1][3].length
           case EArray(l) => {
             indicies(l)           
           }
           case _ => throw new IllegalArgumentException("Operation indicies on " + obj + " is not defined - must be EArray")
         }
         case "field" => obj match {
           case EObject(o) => {
             if(args.length==0 || args.length >2) throw new IllegalArgumentException("Operation field requires 1 or 2 arguments not " + args.length)
             args(0) match {
               case Var(v) => o match {
                 case f:uk.ac.stand.impl.FlagUser => {
                   if(args.length==1) return convert(f.getFlagValue(v))
                   eval(args(1)) match { //Flag user second argument is the index into a field
                     case EInt(i) => convert(f.getFlagValue(v,i))
                     case EBool(true) => convert(f.getFlagValue(v,1))
                     case EBool(false) => convert(f.getFlagValue(v,0))
                     case x => throw new IllegalArgumentException("Operation field requires 2nd argument to be an integer not " + x)
                   }
                 }
                 //Have to use asInstanceOf to get around scala/java implicit conversion issue
                 //Java relection method to get a field
                 case _ => convert(o.asInstanceOf[java.lang.Object].getClass.getField(v)) 
               }
               case _ => throw new IllegalArgumentException("Operation field requires 1 argument, a string not " + args(0))
             }
           }
           case _ => throw new IllegalArgumentException("Operation field on " + obj + " is not defined - must be an object")
         }
       }
     }
    }
  } 
  
  def indicies(l:Array[Value]): EArray = {   
    EArray(indicies_h(l).map(x=> ERange(0,x-1)).toArray)
  }
  
  private def indicies_h(a:Array[Value]): List[Int] = {
    a(0) match {
      case EArray(sub) => a.length :: indicies_h(sub)
      case _ => List(a.length)
    }
  }
  
           /*
createSetting(name, type, default value)
createTeamField(name, type, default value)
createSpeakerField(name, type, default value)
          */
  def createArguments(a:List[Exp]): (String, java.lang.Class[_], java.lang.Object) = {
    
    val x = new java.lang.Object //Doesn't like java.lang.Object.TYPE
    var t:java.lang.Class[_] = x.getClass
    var default:java.lang.Object = null
      
    a(0) match { //Don't evaluate as want the Var node's Id
      case Var(name) =>
        a(1) match { //Don't evaluate as want the Var node's Id - should evaluate to a class name
          case Var("Int") =>
            t = java.lang.Integer.TYPE
          case Var("Boolean") =>
            t = java.lang.Boolean.TYPE
          case Var(x) => throw new IllegalArgumentException("Create settings or fields take as its second argument a class (currently Int or Boolean), not " + x)
        }
        eval(a(2)) match {
          case EInt(x) => default = new java.lang.Integer(x)
          case EBool(x) => default = new java.lang.Boolean(x)
          case x => throw new IllegalArgumentException("Create settings third argument is a default value. It must evaluate to and Int or Boolean value, not " + x)
        }
        return (name, t, default)
        
      case x => throw new IllegalArgumentException("Create settings or fields take as its first argument a name not " + x)
    }
  }
  
  def createSetting(a:List[Exp]) {
    val args = createArguments(a)
    
    settings += args._1 -> (args._2, args._3)
  }
  
  def createTeamField(a:List[Exp]) {
    val args = createArguments(a)
    
    teamFields += args._1 -> (args._2, args._3)
  }
  
  def createSpeakerField(a:List[Exp]) {
    val args = createArguments(a)
    
    speakerFields += args._1 -> (args._2, args._3)
  }
         
  def addValidateSetting(a:Id, s:Stats) {
    validSettingsFuncs += a -> s    
  }
  
  def addValidateTeamField(a:Id, s:Stats) {
    validTeamFieldsFuncs += a -> s
  }
  
  def addValidateSpeakerField(a:Id, s:Stats) {
    validSpeakerFieldsFuncs += a -> s
  }
  
  def setValidateResult(s:Stats) {
    roomValidiationFunc = Some(s)
  }
  
  def convert(o:Object): Value = {
    o match {
      case null => throw new IllegalArgumentException("Trying to convert a null object")
      case i:java.lang.Integer => EInt(i.intValue)
      case l:java.util.List[Object] => {
        EArray(l.toList.map(x=> convert(x)).toArray) //If it is a list convert every item individually (handles lists of lists)
      }
      case b:java.lang.Boolean => EBool(b.booleanValue)
      case c:java.util.Collection[Object] => EArray(c.toArray.map(x=>convert(x)))
      case y => throw new IllegalArgumentException("Currently can only convert Integer, Boolean and Collections")
    }
  }
  
  //Checks whether a given array index will fail for more graceful handling - throws an exception and return booleans
  def boundsCheck(index: Int, length: Int): Boolean = {
    if(index<0 || index > length) throw new IllegalArgumentException("Array index " + index + " is invalid"); false
    true
  }
  
  def param(a:List[Exp]): (Id, Value) = {
    /*
        * Creates a parameter in the essence files
        * a(0) = name
        * a(1) = data - Int, Bool or Array (1 dimension)
        * Will give bounds for the array based on the actual values - use paramMatrix for anything more
        */
       
       if(a.length!=2) throw new IllegalArgumentException("Function param requires 2 arguments not " + a.length + " : " + a)
       
       a(0) match {
         case Var(v) => 
           val arg = eval(a(1)) 
           arg match {
           case x:EInt => emain.append("given " + v + " : int\n"); eparam.append("parameter " + v + " is " + x.v + "\n"); (v, x)
           case EBool(true) => emain.append("given " + v + " : int\n"); eparam.append("parameter " + v + " is 1\n"); (v, EBool(true))
           case EBool(false) => emain.append("given " + v + " : int\n"); eparam.append("parameter " + v + " is 0\n"); (v, EBool(false))
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
             (v, EArray(l))
           }
         }
         case v => throw new IllegalArgumentException("Function param requires first argument to be a Variable not " + v)
       }
  }
  
  def paramMatrix(a:List[Exp]): (Id, Value) = {
    /*Creates a parameter for a matrix
       * a(0) = name
       * a(1) = EArray containing data - can be multi dimension
       * a(2) = EArray containing the ranges for the indexed of the array - must correspond to data indicies - use e.g. array(range(0,5), range(0,10))
       * a(3) - Range for the array data itself - actual data must lie within range
       */
       if(a.length!=4) throw new IllegalArgumentException("Function paramMatrix takes 4 arguments not " + a.length)
       
       a(0) match {
         case Var(v) => eval(a(1)) match {
           case EArray(array) => {
             eval(a(2)) match {
               case EArray(ranges) => {
                 eval(a(3)) match {
                   case bound:ERange => {
                     //All the arguments now verified
                     
                     val rlist = ranges.map(x=> x match {
                       case r:ERange => r
                       case _ => throw new IllegalArgumentException("Function paramMatrix requires all values in the array of its 3rd argument to evaluate to a range")
                     }).toList
                     
                     println("***ranges len " + ranges.length)
                     //rlist now is a list of the ranges - now fold the list of domains into a string
                     val pstring = if(rlist.length>1) {
                       "[" + rlist.tail.map(x=>x.domain).foldLeft(rlist.head.domain)(_ + ", " + _) + "]"
                     } else {
                       "[" + rlist(0).domain + "]" 
                     }
                     
                     emain.append("given " + v + " : matrix indexed by " + pstring + " of " + bound.domain + "\n")
                     eparam.append("parameter " + v + " is " + printMatrix(array, rlist) + "\n")
                     
                     (v, EArray(array))
                   }
                   case v => throw new IllegalArgumentException("Function paramMatrix requires its fourth argument to be a Range not " + v)
                 }
               }
               case v => throw new IllegalArgumentException("Function paramMatrix requires its third argument to be an array of Ranges not " + v)
             }
           }
           case v => throw new IllegalArgumentException("Function paramMatrix requires second argument to be an array not " + v)
         }
         case v => throw new IllegalArgumentException("Function paramMatrix requires first argument to be a Variable not " + v)
       }
  }
  
  def printMatrix(a:Array[Value], l:List[ERange]): String = {
    if(l==Nil) throw new IllegalArgumentException("Passed array for printing has too many indices")
    
    val r:List[String] = a.slice(l.head.l, l.head.u+1).map(y => y match {
      case EInt(v) => v.toString
      case EBool(true) => "1"
      case EBool(false) => "0"
      case EArray(sa) => printMatrix(sa, l.tail)
    }).toList
      
    "[" + r.tail.foldLeft(r.head.toString)(_ + ", " + _.toString) + "]"
  }
  
  def freqArraySub(a:Array[Value], r:ERange): Array[Value] = {
    //Takes an array of values and if the first is not an EArray produces a frequency array of the values (ignores any EArrays within)
    
    if(a.length==0) {//throw new IllegalArgumentException("freqArray - passed array had no entries")
      //Just return array of 0s
       return (r.l to r.u).map(y=> EInt(0)).toArray
    } 
    
    a(0) match {
      case EArray(l) => a.map(x=> EArray(freqArraySub(x.asInstanceOf[EArray].v,r))).toArray
      case x => {
        val offset = r.l
        val ret = new Array[Int](r.u-r.l+1) //Inclusive range so add 1
        
        for(z<-0 to ret.length-1) ret(z) = 0
        
        a.foreach(y => y match {
          case EInt(v) => 
            //println("val: " + v + " check " + r.check(v) + " incrementing " + (v-offset))
            if(r.check(v)) ret(v-offset) += 1 //Discard if not in the range
          case EBool(true) => if(r.check(1)) ret(1-offset) +=1
          case EBool(false) => if(r.check(0)) ret(0-offset) +=1 //Offset could be a negative number so still use 
        })
        
        ret.map(y=>EInt(y))
      }
    }

  }
  
  def freqArray(a:List[Exp]): EArray = {
    /*For each array creates a new array x where x(i) is the frequency of i in the original array nb. assumes start from 0, shift i accordingly otherwise
     * If a multi dimension array is passed then the last array is turned into a frequency array
	 * a(0) = EArray
	 * a(1) = ERange - any values outside the range are ignored
	*/
   
   if(a.length!=2) throw new IllegalArgumentException("Function freqArray requires 2 arguments not " + a.length + " : " + a)
   
   eval(a(0)) match {
     case EArray(l) => {
       eval(a(1)) match {
         case r:ERange => {
           EArray(freqArraySub(l,r)) 
         }
         case x => throw new IllegalArgumentException("Function freqArray requires a range as the second argument, not " + x) 
       }
     }
     case x => throw new IllegalArgumentException("Function freqArray requires an array not " + x)
   }
  }
  
  def array(a:List[Exp]): EArray = {
    //Takes a list of Expressions, evaluates them and returns an EArray containing them
   
   val nl = List[Value]()
   
   val al = a.map(y => eval(y))
   for(x <- al) x match {
     case a:Value => nl ::: List(a)
     case _ => throw new IllegalArgumentException("Function array takes only arguments that evaluate to values: " + x)
   }
   
   EArray(nl.toArray)
  }
  
  def range(a:List[Exp]): ERange = {
    //Takes two arguments, min and max and returns a range object for expressing domain min..max
   
   if(a.length!=2) throw new IllegalArgumentException("Function range takes 2 arguments not " + a.length)
   
   (eval(a(0)), eval(a(1))) match {
     case (EInt(min), EInt(max)) => ERange(min, max)
     case (l,r) => throw new IllegalArgumentException("Function range takes 2 integers not " + l + ", " + r)
   }
  }
  
  def createArray(a:List[Exp]): EArray = {
    //Creates an EArray with capacity x
    
    if(a.length!=1) throw new IllegalArgumentException("Function createArray takes 1 argument not " + a.length)
    
    eval(a(0)) match {
      case EInt(s) => EArray(new Array[Value](s))
      case x => throw new IllegalArgumentException("Function createArray requires an integer not " + x)
    }
  }
  
  def pastPositions(): EArray = {
    //Creates an array - size number of teams, where each element is an array 
    
    val m = uk.ac.stand.impl.Competition.getInstance.getDraws
    val teams = uk.ac.stand.impl.Competition.getInstance.getTeams.toList
    val npos = uk.ac.stand.impl.Position.getPositionArray.length
    
    val pp = new Array[List[Int]](teams.length)
    for(x<- 0 to teams.length-1) {
      pp(x) = List[Int]()
    }
    
    m.keySet.foreach(x=> m(x).getRooms.foreach(y=> {
     val mr = y.getTeams
     mr.keySet.foreach(z => {
       pp(teams.indexOf(mr(z))) = z.absNumber.intValue :: pp(teams.indexOf(mr(z)))
     })
    }))
    
    EArray(pp.map(x=> EArray(x.map(y=> EInt(y)).toArray)))
  }
  
}


