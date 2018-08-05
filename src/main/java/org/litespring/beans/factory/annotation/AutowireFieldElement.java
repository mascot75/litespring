package org.litespring.beans.factory.annotation;

import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.AutowireCapableBeanFactory;
import org.litespring.beans.factory.config.DependencyDescriptor;
import org.litespring.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author Jack
 */
public class AutowireFieldElement extends InjectionElement {
    private boolean required;

    public AutowireFieldElement(Field field, boolean required, AutowireCapableBeanFactory factory) {
        super(field, factory);
        this.required = required;
    }

    public Field getField() {
        return (Field) this.member;
    }

    @Override
    public void inject(Object target) {
        Field field = this.getField();
        try {
            DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field, required);
            Object value = factory.resolveDependency(dependencyDescriptor);
            if (value != null) {
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        } catch (Throwable ex) {
                throw new BeanCreationException("Could not autowire field: " + field, ex);
        }

    }
}
