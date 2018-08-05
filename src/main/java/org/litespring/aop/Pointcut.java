package org.litespring.aop;

/**
 * @author Jack
 */
public interface Pointcut {
    MethodMatcher getMethodMatcher();
    String getExpression();
}
