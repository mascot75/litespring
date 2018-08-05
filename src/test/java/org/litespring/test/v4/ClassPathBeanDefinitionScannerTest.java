package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import org.litespring.context.annotation.ScannedGenericBeanDefinition;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.stereotype.Component;

public class ClassPathBeanDefinitionScannerTest {
    @Test
    public void testParseScannedBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        String basePackage = "org.litespring.service.v4,org.litespring.dao.v4";
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackage);
        String annotation = Component.class.getName();

        {
            BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
            Assert.assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);

            ScannedGenericBeanDefinition definition = (ScannedGenericBeanDefinition)beanDefinition;
            AnnotationMetadata metadata = definition.getMetadata();
            Assert.assertTrue(metadata.hasAnnotation(annotation));

            AnnotationAttributes attributes = metadata.getAnnotationAttributes(annotation);
            Assert.assertEquals("petStore", attributes.get("value"));
        }

        {
            BeanDefinition beanDefinition = factory.getBeanDefinition("accountDao");
            Assert.assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);

            ScannedGenericBeanDefinition definition = (ScannedGenericBeanDefinition)beanDefinition;
            AnnotationMetadata metadata = definition.getMetadata();
            Assert.assertTrue(metadata.hasAnnotation(annotation));
        }

        {
            BeanDefinition beanDefinition = factory.getBeanDefinition("itemDao");
            Assert.assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);

            ScannedGenericBeanDefinition definition = (ScannedGenericBeanDefinition)beanDefinition;
            AnnotationMetadata metadata = definition.getMetadata();
            Assert.assertTrue(metadata.hasAnnotation(annotation));
        }
    }
}
