package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

import java.util.List;

public class BeanDefinitionTest {
    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;

    @Before
    public void setUp() throws Exception {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore_v2.xml"));
    }

    @Test
    public void testGetBeanDefinition() {
        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();

        Assert.assertTrue(propertyValueList.size() == 4);

        PropertyValue propertyValue1 = this.getPropertyValue("accountDao", propertyValueList);
        Assert.assertNotNull(propertyValue1);
        Assert.assertTrue(propertyValue1.getValue() instanceof RuntimeBeanReference);

        PropertyValue propertyValue2 = this.getPropertyValue("itemDao", propertyValueList);
        Assert.assertNotNull(propertyValue2);
        Assert.assertTrue(propertyValue2.getValue() instanceof RuntimeBeanReference);
        
    }

    private PropertyValue getPropertyValue(String name,List<PropertyValue> pvs){
        for(PropertyValue pv : pvs){
            if(pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }
}
