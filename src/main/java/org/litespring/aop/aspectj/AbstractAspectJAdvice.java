package org.litespring.aop.aspectj;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

/**
 * @author Jack
 */
public abstract class AbstractAspectJAdvice implements Advice {
    private Method adviceMethod;
    private AspectJExpressionPointcut pointcut;
    private AspectInstanceFactory adviceObjectFactory;

    public AbstractAspectJAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, AspectInstanceFactory adviceObjectFactory) {
        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObjectFactory = adviceObjectFactory;
    }

    public void invokeAdviceMethod() throws  Throwable{
        adviceMethod.invoke(adviceObjectFactory.getAspectInstance());
    }

    @Override
    public Pointcut getPointcut(){
        return this.pointcut;
    }

    public Method getAdviceMethod() {
        return adviceMethod;
    }

    public  Object getAdviceInstance() throws Exception {
        return adviceObjectFactory.getAspectInstance();
    }
}
