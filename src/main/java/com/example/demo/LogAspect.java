package com.example.demo;

import org.aopalliance.intercept.Joinpoint;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	private static final Logger LOG = Logger.getLogger(LogAspect.class);

	public LogAspect() {
	}

	@Around("@annotation(LogMethodExecution)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		LOG.info(joinPoint.getClass());
		LOG.info("[---] ## > Entering " + signature.getDeclaringTypeName() + "::" + signature.getName() + "(...)");
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		LOG.info("[---] ## > Exiting " + signature.getDeclaringTypeName() + "::" + signature.getName() + "(...)");
		LOG.info("## > " + signature.getName() + "(...) executed in " + executionTime + "ms");
		return proceed;
	}
	
//	@AfterReturning("execution(com.example.demo*(..)")
//	public void logMethodExecutionFinish(Joinpoint jp) {
//		LOG.info("<---------------------------------------->");
//	}
}
