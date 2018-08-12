package org.litespring.aop.framework;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;
import org.litespring.util.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack
 */
public class AopConfigSupport implements AopConfig {
    private boolean proxyTargetClass = false;
    private Object targetObject = null;
    private List<Advice> advices = new ArrayList<Advice>();
    private List<Class> interfaces = new ArrayList<Class>();

    @Override
    public Class<?> getTargetClass() {
        return targetObject.getClass();
    }

    @Override
    public void setTargetObject(Object obj) {
        this.targetObject = obj;
    }

    @Override
    public Object getTargetObject() {
        return targetObject;
    }

    @Override
    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    @Override
    public Class<?>[] getProxiedInterfaces() {
        return this.interfaces.toArray(new Class[this.interfaces.size()]);
    }

    @Override
    public boolean isInterfaceProxied(Class<?> intf) {
        for (Class proxyIntf : this.interfaces) {
            if (intf.isAssignableFrom(proxyIntf)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Advice> getAdvices() {
        return this.advices;
    }

    @Override
    public void addAdvice(Advice advice) {
        this.advices.add(advice);
    }

    @Override
    public List<Advice> getAdvices(Method method) {
        List<Advice> result = new ArrayList<>();
        for(Advice advice : this.getAdvices()){
            Pointcut pc = advice.getPointcut();
            if(pc.getMethodMatcher().matches(method)){
                result.add(advice);
            }
        }
        return result;
    }

    public void addInterface(Class<?> intf) {
        Assert.notNull(intf, "Interface must not be null");
        if (!intf.isInterface()) {
            throw new IllegalArgumentException("[" + intf.getName() + "] is not an interface");
        }
        if (!this.interfaces.contains(intf)) {
            this.interfaces.add(intf);

        }
    }

}
