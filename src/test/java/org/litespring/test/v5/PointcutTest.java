package org.litespring.test.v5;

import org.junit.Test;
import org.litespring.aop.MethodMatcher;
import org.litespring.aop.aspectj.AspectJExpressionPointcut;
import org.litespring.service.v5.PetStoreService;

import java.lang.reflect.Method;

import static org.junit.Assert.*;


public class PointcutTest {
    @Test
    public void testPointcut() throws NoSuchMethodException {
        String expression = "execution(* org.litespring.service.v5.*.placeOrder(..))";

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        MethodMatcher methodMatcher = pointcut.getMethodMatcher();

        Class<?> targetClass = PetStoreService.class;
        Method method1 = targetClass.getMethod("placeOrder");
        assertTrue(methodMatcher.matches(method1));

        Method method2 = targetClass.getMethod("getAccountDao");
        assertFalse(methodMatcher.matches(method2));

        Class<?> target = org.litespring.service.v4.PetStoreService.class;
        Method method3 = target.getMethod("getAccountDao");
        assertFalse(methodMatcher.matches(method3));
    }
}
