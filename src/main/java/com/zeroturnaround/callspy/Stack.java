package com.zeroturnaround.callspy;

import java.io.PrintWriter;

@SuppressWarnings("unused")
public class Stack {

	public static final String SPACE = " ";
	private static int lockOnHeight = Integer.MAX_VALUE;
	public static Boolean outputToConsole = false;
	public static String outputFile = "";
	public static StringBuffer stringBuffer;

	static String ident = "";

//	private static Object lock = new Object();

	public static void push() {
		ident += SPACE;
	}

	public static void pop() {
		ident = ident.substring(1);
	}

	public static void log(String string) {
		if (outputToConsole) {
			System.out.println(ident + string);
		}
		if (stringBuffer != null)
			stringBuffer.append(ident.substring(1)).append(string).append("\n");
	}

	//
	public static int height() {
		return ident.toCharArray().length;
	}

	public static int getLockOnHeight() {
		return lockOnHeight;
	}

	public static void setLockOnHeight(int lockOnHeight) {
		Stack.lockOnHeight = lockOnHeight;
	}

	public static void flushToFile() throws Exception {
		if (stringBuffer != null)
			try (PrintWriter out = new PrintWriter(outputFile)) {
				out.print(stringBuffer);
			}
	}
}
