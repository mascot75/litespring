package org.litespring.aop;

import java.lang.reflect.Method;

/**
 * @author Jack
 */
public interface MethodMatcher {
    boolean matches(Method method /*, Class<?> targetClass*/);
}
