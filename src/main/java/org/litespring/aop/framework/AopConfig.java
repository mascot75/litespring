package org.litespring.aop.framework;

import java.lang.reflect.Method;
import java.util.List;

import org.litespring.aop.Advice;

/**
 * @author Jack
 */
public interface AopConfig  {

	Class<?> getTargetClass();
	
	Object getTargetObject();

	boolean isProxyTargetClass();

	Class<?>[] getProxiedInterfaces();

	boolean isInterfaceProxied(Class<?> intf);
	
	List<Advice> getAdvices();
	
	void addAdvice(Advice advice) ;

	List<Advice> getAdvices(Method method/*,Class<?> targetClass*/);

	void setTargetObject(Object obj);

}
