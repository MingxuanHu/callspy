package org.example.callspy.example;

import java.lang.reflect.Method;

import net.bytebuddy.implementation.bind.annotation.Origin;

public class OriginHelloWorld {
	// byte buddy can use annotation to access the intercepted method
	public static String intercept(@Origin Method m) {
		System.out.println(m.getDeclaringClass());
		return "Intercepted! Hello World from " + m.getName();
	}
}
