package com.zeroturnaround.callspy;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent {

	public static void premain(String args, Instrumentation instrumentation) {
//		System.out.println("Agent: Hi JavaOne!");
//
//		System.out.println("Redefine classes: " + instrumentation.isRedefineClassesSupported());
//		System.out.println("Retransform classes: " + instrumentation.isRetransformClassesSupported());

		AgentBuilder agentBuilder = createAgentBuilder();
		agentBuilder.installOn(instrumentation);

	}

	private static AgentBuilder createAgentBuilder() {
		return new AgentBuilder.Default()
//				.type(ElementMatchers.nameStartsWith("org.example"))
				.type(ElementMatchers.nameStartsWith("org.example").or(
						ElementMatchers.nameStartsWith("org.springframework")))
				.transform((builder, typeDescription, classLoader) -> {
//					System.out.println("Transforming " + typeDescription);
					return builder.visit(Advice.to(MyAdvice.class).on(ElementMatchers.any()));
				});
	}
}
