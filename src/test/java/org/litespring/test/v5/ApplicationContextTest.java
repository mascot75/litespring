package org.litespring.test.v5;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v5.PetStoreService;
import org.litespring.util.MessageTracker;

import java.util.List;

import static org.junit.Assert.*;

public class ApplicationContextTest {
    @Test
    public void testPlaceOrder() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore_v5.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");

        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());
        petStore.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();

        assertEquals(3, msgs.size());
        assertEquals("start tx", msgs.get(0));
        assertEquals("place order", msgs.get(1));
        assertEquals("commit tx", msgs.get(2));
    }
}
