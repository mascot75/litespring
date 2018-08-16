package org.litespring.beans;

import java.util.List;

/**
 * @author mascot
 */
public interface BeanDefinition {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";

    boolean isSingleton();
    boolean isPrototype();
    String getScope();
    void setScope(String scope);

    String getBeanClassName();

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    String getID();

    boolean hasConstructorArgumentValues();

    Class<?> getBeanClass() throws IllegalStateException;

    Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;

    boolean hasBeanClass();

    boolean isSynthetic();
}
