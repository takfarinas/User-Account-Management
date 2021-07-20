package com.technical.test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggerComponent {
    Logger logger = Logger.getLogger(LoggerComponent.class.getName());

    @Around("@annotation(com.technical.test.aspect.Loggable)")
    Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Long t1 = System.currentTimeMillis();
        Object result =  joinPoint.proceed();
        Long t2 = System.currentTimeMillis();

        logger.info(String.format("Processing : %s | Processing Time : %s ms", constructLogMsg(joinPoint, result), (t2-t1)));

        return result;
    }

    private String constructLogMsg(JoinPoint jp, Object result) {

        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        StringBuilder sb = new StringBuilder("@");
        sb.append(method.getName());
        sb.append(": ");

        if (jp.getArgs().length > 0) {
            String args = Arrays.asList(jp.getArgs()).stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]"));
            sb.append("Inputs -> ");
            sb.append(args);
        }

        if (result != null ){
            sb.append(" Outputs -> ");
            sb.append(result);
        }

        return sb.toString();
    }
}
