package org.example.callspy.example;

/**
 * Created by shelajev on 27/04/15.
 */
public class RandomClassCallingThings {

	public static void main(String[] args) {
		callThings();
	}

	public static void callThings() {
		System.out.println("And we start calling things");
		B b = new B();
		b.setIdx(4);
		b.op("Hello world!");
		b.setIdx(3);
		b.op("A");
		System.out.println("And we're done!");
	}

} 
