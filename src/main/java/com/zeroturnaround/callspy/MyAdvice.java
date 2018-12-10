package com.zeroturnaround.callspy;


import net.bytebuddy.asm.Advice;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
	public static boolean manual = false;
	public static Set<String> noSet;
	static {
		noSet = new HashSet<>();
		noSet.add("n");
		noSet.add("no");
		noSet.add("N");
		noSet.add("No");
		noSet.add("f");
		noSet.add("false");
		noSet.add("F");
		noSet.add("False");
	}
	
	  @Advice.OnMethodEnter
	  public static void enter(
	  		@Advice.Origin Class klass,
			@Advice.Origin("#m") String methodName,
			@Advice.Origin("#s") String signature,
			@Advice.Origin("#r") String returnType
	  ) {
		Stack.push();
		if (manual) {
			if (Stack.height() > Stack.getLockOnHeight()) {
				return;
			}
			Stack.setLockOnHeight(Integer.MAX_VALUE);

			System.out.println("========================================================");
			System.out.println("-- " + klass.getTypeName() + "." + methodName + signature + ": " + returnType);
			System.out.print("-- Do you want to log the method above (y)?");
			String input = new Scanner(System.in).nextLine();
			if (noSet.contains(input.trim())) {
				Stack.setLockOnHeight(Stack.height());
				return;
			}
		}
		Stack.log(klass.getTypeName() + "." + methodName + signature + ": " + returnType);
	  }
	  
	  @Advice.OnMethodExit
	  public static void exit() {
		Stack.pop();
	  }
	}