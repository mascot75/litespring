package org.litespring.context.support;

import org.litespring.aop.aspectj.AspectJAutoProxyCreator;
import org.litespring.beans.factory.NoSuchBeanDefinitionException;
import org.litespring.beans.factory.annotation.AutowireAnnotationProcessor;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

import java.util.List;

/**
 * @author mascot
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        this(configFile, ClassUtils.getDefaultClassLoader());
    }

    public AbstractApplicationContext(String configFile, ClassLoader classLoader) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
        factory.setBeanClassLoader(classLoader);
        this.registerBeanPostProcessor(factory);
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    protected void registerBeanPostProcessor(ConfigurableBeanFactory beanFactory) {
         AutowireAnnotationProcessor processor = new AutowireAnnotationProcessor();
         processor.setBeanFactory(beanFactory);
         beanFactory.addBeanPostProcessors(processor);

         AspectJAutoProxyCreator proxyCreator = new AspectJAutoProxyCreator();
         proxyCreator.setBeanFactory(beanFactory);
         beanFactory.addBeanPostProcessors(proxyCreator);
    }

    @Override
    public Class<?> getType(String beanName) throws NoSuchBeanDefinitionException {
        return this.factory.getType(beanName);
    }

    @Override
    public List<Object> getBeansByType(Class<?> type) {
        return this.factory.getBeansByType(type);
    }

    protected abstract Resource getResourceByPath(String path);
}
