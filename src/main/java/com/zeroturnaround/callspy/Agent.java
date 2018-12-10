package com.zeroturnaround.callspy;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent {

	public static void premain(String args, Instrumentation instrumentation) {
		AgentBuilder agentBuilder = createAgentBuilder();
		agentBuilder.installOn(instrumentation);

	}

	private static AgentBuilder createAgentBuilder() {
		return new AgentBuilder.Default()
				.type(ElementMatchers.nameMatches(CallspyConfig.getStartWithRegex()))
				.transform((builder, typeDescription, classLoader) ->
						builder.visit(Advice.to(MyAdvice.class).on(ElementMatchers.any())));
	}
}
