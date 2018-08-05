package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * @author Jack
 */
public class AspectJBeforeAdvice implements Advice {

    protected Method adviceMethod;
    protected AspectJExpressionPointcut pointcut;
    protected Object adviceObject;

    public AspectJBeforeAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        adviceMethod.invoke(adviceObject);
        return methodInvocation.proceed();
    }
}
