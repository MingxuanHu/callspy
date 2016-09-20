package org.example.callspy.example;

import java.lang.reflect.Method;

import net.bytebuddy.implementation.bind.annotation.Origin;

public class OriginHelloWorld {
	public static String intercept(@Origin Method m) {
		return "Intercepted! Hello World from " + m.getName();
	}
}
