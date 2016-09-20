package org.example.callspy.example;

import java.lang.reflect.Method;

import net.bytebuddy.implementation.bind.annotation.Origin;

public class HelloWorld {
	public static String intercept() {
		return "Intercepted! Hello World";
	}
}

