package org.litespring.beans.factory.config;

import java.util.List;

/**
 * @author Jack
 */
public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {
	void setBeanClassLoader(ClassLoader beanClassLoader);

	ClassLoader getBeanClassLoader();

	void addBeanPostProcessors(BeanPostProcessor postProcessor);

	List<BeanPostProcessor> getBeanPostProcessors();
}
