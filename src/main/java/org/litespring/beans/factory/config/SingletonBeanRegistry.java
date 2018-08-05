package org.litespring.beans.factory.config;

/**
 * @author Jack
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);
    Object getSingleton(String beanNeme);
}
