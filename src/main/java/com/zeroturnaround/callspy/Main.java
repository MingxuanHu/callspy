package com.zeroturnaround.callspy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.example.callspy.example.OriginHelloWorld;

public class Main {
	
	public static void main(String[] args) throws Exception {
		// System.out.println("Hello from CallSpy!");
		// RandomClassCallingsThings.callThings();

//		// create a dynamic type class on the fly
//		Class<?> dynamicType = new ByteBuddy()
//				.subclass(Object.class)
//				.method(ElementMatchers.named("toString"))
//				.intercept(FixedValue.value("Hello World JavaOne"))
//				.make()
//				.load(Main.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
//				.getLoaded();
//
//		System.out.println(dynamicType.newInstance());
		
		
		
		
		
		
		
//		// intercept at run time and delegate to another class
//		Class<?> intercepted = new ByteBuddy()
//				  .subclass(Object.class)
//				  .method(ElementMatchers.named("toString"))
//				  .intercept(MethodDelegation.to(HelloWorld.class))
//				  .make()
//				  .load(Main.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
//				  .getLoaded();
//
//
//		System.out.println(intercepted.newInstance());
		
		
		
		
		

		Class<?> interceptedWithOrigin = new ByteBuddy()
				  .subclass(Object.class)
				  .method(ElementMatchers.named("toString"))
				  .intercept(MethodDelegation.to(OriginHelloWorld.class))
				  .make()
				  .load(Main.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				  .getLoaded();


		System.out.println(interceptedWithOrigin.newInstance());

	}
}
