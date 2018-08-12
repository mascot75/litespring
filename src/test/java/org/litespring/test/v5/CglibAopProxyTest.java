package org.litespring.test.v5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectj.AspectJBeforeAdvice;
import org.litespring.aop.aspectj.AspectJExpressionPointcut;
import org.litespring.aop.framework.AopConfig;
import org.litespring.aop.framework.AopConfigSupport;
import org.litespring.aop.framework.CglibProxyFactory;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;
import org.litespring.util.MessageTracker;

import java.util.List;

public class CglibAopProxyTest {
    private static AspectJBeforeAdvice beforeAdvice = null;
    private static AspectJAfterReturningAdvice afterAdvice = null;

    @Before
    public  void setUp() throws Exception{
        TransactionManager tx = new TransactionManager();
        String expression = "execution(* org.litespring.service.v5.*.placeOrder(..))";
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getMethod("start"), pc, tx);
        afterAdvice = new AspectJAfterReturningAdvice(TransactionManager.class.getMethod("commit"), pc, tx);

        MessageTracker.clearMsgs();
    }

    @Test
    public void testGetProxy() {
        AopConfig config = new AopConfigSupport();

        config.addAdvice(beforeAdvice);
        config.addAdvice(afterAdvice);
        config.setTargetObject(new PetStoreService());

        CglibProxyFactory proxyFactory = new CglibProxyFactory(config);
        PetStoreService proxy = (PetStoreService) proxyFactory.getProxy();
        proxy.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));

        proxy.toString();
    }
}
