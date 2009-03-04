---
layout: post
title: Java function pointers (sort of)
---

Having an interpreter written in Java as part of my project is creating all sorts of fun. Not for me the joys of dynamic typing.
I've been trying to work out a way of allowing the interpreted language to supply (or perhaps even *override* if I'm brave) functions in either an object oriented or functional style for a specific implementation.
Function pointers would let me do some really easy implementation in terms of allowing classes implementing a certain interface to specify the functions they have built in and then execute those functions/methods.

This all builds in to the idea of *flags* I'm using. Storing objects associated with a class instance, whereby all the instances of the class will return something with a call to getFlag(flag), e.g. team.getFlag(totalscore) would return the total score thus far for the team.
The clever part comes in allowing flags to also be functions, basically implementing object orientation in a more dynamic way. Previously totalscore would have had to be updated every time a new score was added. Now a builtin function calculates it on demand. Plus it could be overridden if a different means of calculating a score other than adding all the subscores was ever invented (who knows, score is probably a bad example for this).

Now when the interpreter comes across a new function definition it can add it to the list of functions for a given class (or general functions in the case of no class). Currently I'm using ANTLR so there will be a specific class that will store an ANTLR node and then execute that on calling. Changing over to another interpreter system should be as simple (in this part at least) as creating a new class implementing an interface which runs this other type of interpreter. It should even be possible to build in Jython or something in the future too.