package org.litespring.beans.factory;

/**
 * @author Jack
 */
public interface BeanFactory {
    Object getBean(String beanId);

    Class<?> getType(String beanName) throws NoSuchBeanDefinitionException;
}
