package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.TypeMismatchException;

public class TypeConverterTest {
    TypeConverter converter;
    @Before
    public void setUp() throws Exception {
        converter = new SimpleTypeConverter();
    }

    @Test
    public void testConverterStringToInt() {
        Integer i = converter.convertIfNecessary("4", Integer.class);
        Assert.assertEquals(4, i.intValue());

        try{
            converter.convertIfNecessary("4.1", Integer.class);
        }catch(TypeMismatchException e){
            return;
        }
        Assert.fail();
    }

    @Test
    public void testConverterStringToBoolean() {
        Boolean b = converter.convertIfNecessary(true, Boolean.class);
        Assert.assertEquals(true, b);

        b = converter.convertIfNecessary("off", Boolean.class);
        Assert.assertEquals(false, b);
        try{
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        }catch(TypeMismatchException e){
            return;
        }
        Assert.fail();
    }
}
