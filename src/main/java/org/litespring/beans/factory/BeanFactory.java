package org.litespring.beans.factory;

import org.litespring.aop.Advice;

import java.util.List;

/**
 * @author Jack
 */
public interface BeanFactory {
    Object getBean(String beanId);

    Class<?> getType(String beanName) throws NoSuchBeanDefinitionException;

    List<Object> getBeansByType(Class<?> type);
}
