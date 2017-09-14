package com.example.demo.application.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * This class is responsible to create aop aspect for this rest app. At present
 * we demonstrate annotation based log framework.
 * 
 * @author KMaji
 *
 */
@Aspect
@Component
public class LogAspect {

	private static final Logger LOG = Logger.getLogger(LogAspect.class);

	public LogAspect() {
	}

	/**
	 * This method log the execution timeline of business methods which are
	 * annotated with {@link LogMethodExecution}
	 * 
	 * @param joinPoint
	 *            this provides the entry point of this rest app.
	 * @return 
	 * @throws Throwable
	 */
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
}
