package com.zeroturnaround.callspy;

@SuppressWarnings("unused")
public class Stack {

	private static final String SPACE = " ";
	static String ident = "";

	public static void push() {
		ident += SPACE;
	}

	public static void pop() {
		ident = ident.substring(1);
	}

	public static void log(String string) {
		System.out.println(ident + string);
	}

}
