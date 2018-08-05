package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

import java.util.List;

public class BeanDefinitionTest {
    @Test
    public void testConstructorArgument() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore_v3.xml");
        reader.loadBeanDefinitions(resource);

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        Assert.assertEquals("org.litespring.service.v3.PetStoreService", beanDefinition.getBeanClassName());

        ConstructorArgument argument = beanDefinition.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> valueHolders = argument.getArgumentValues();

        Assert.assertEquals(3, valueHolders.size());

        RuntimeBeanReference reference1 = (RuntimeBeanReference) valueHolders.get(0).getValue();
        Assert.assertEquals("accountDao", reference1.getBeanName());
        RuntimeBeanReference reference2 = (RuntimeBeanReference) valueHolders.get(1).getValue();
        Assert.assertEquals("itemDao", reference2.getBeanName());

        TypedStringValue value = (TypedStringValue) valueHolders.get(2).getValue();
        Assert.assertEquals("1", value.getValue());

    }
}
