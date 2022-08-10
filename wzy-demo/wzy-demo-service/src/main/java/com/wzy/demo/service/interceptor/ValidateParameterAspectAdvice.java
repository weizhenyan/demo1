package com.wzy.demo.service.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ValidateParameterAspectAdvice
 *
 * @author 飞燕
 * @date   2022/8/8 22:03
 */
@Order(1000)
@Aspect
@Component
public class ValidateParameterAspectAdvice {

    private Logger logger = LoggerFactory.getLogger(ValidateParameterAspectAdvice.class);

    @Before("execution(*  com.wzy.demo.service.manager.web.*.*(..))")
    public void before(JoinPoint pjd) {
        Object[] args = pjd.getArgs();
        if (args.length > 0) {
            Object oneParam = args[0];

        }
    }
}
