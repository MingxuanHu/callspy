package com.zeroturnaround.callspy;


import net.bytebuddy.asm.Advice;

/**
 * 
 * 
 * 

public abstract String value
Returns the pattern the annotated parameter should be assigned. By default, the Annotation.toString() representation of the method is assigned. Alternatively, a pattern can be assigned where:
#t inserts the method's declaring type.
#m inserts the name of the method (<init> for constructors and <clinit> for static initializers).
#d for the method's descriptor.
#s for the method's signature.
#r for the method's return type.
Any other # character must be escaped by \ which can be escaped by itself. This property is ignored if the annotated parameter is of type Class.
 
 *
 */

public class MyAdvice {
	
	  @Advice.OnMethodEnter
	  public static void enter(
	  		@Advice.Origin Class klass,
			@Advice.Origin("#m") String methodName,
			@Advice.Origin("#t") String declaringType,
			@Advice.Origin("#s") String signature,
			@Advice.Origin("#r") String returnType
	  ) {
		Stack.push();
//		Stack.log(klass.getSimpleName() + "." + methodName + "()");
		  Stack.log(klass.getTypeName() + "." + methodName + signature + ": " + returnType);
	  }
	  
	  @Advice.OnMethodExit
	  public static void exit() {
		Stack.pop();
	  }
	}