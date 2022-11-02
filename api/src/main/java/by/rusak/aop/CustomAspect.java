package by.rusak.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAspect {
    private static final Logger log = Logger.getLogger(CustomAspect.class);

    @Pointcut("execution(* by.rusak.*.*.*(..))")
    public void aroundPointcut() {
    }

    @Around("aroundPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        long time = System.currentTimeMillis();

        log.info("Method " + joinPoint.getSignature().getName() + " start");

        Object proceed = joinPoint.proceed();

        log.info("Method " + joinPoint.getSignature() + joinPoint.getSignature().getName()
                + " finished " + (System.currentTimeMillis() - time) + " millis ");

        return proceed;
    }

}
