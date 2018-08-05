package org.litespring.beans.factory.annotation;


import java.util.List;

/**
 * @author Jack
 */
public class InjectionMetadata {
    private final Class<?> targetClass;
    private List<InjectionElement> injectionElements;

    public InjectionMetadata(Class<?> targetClass, List<InjectionElement> injectionElements) {
        this.targetClass = targetClass;
        this.injectionElements = injectionElements;
    }

    public void inject(Object target) {
        if (injectionElements == null || injectionElements.isEmpty()) {
            return;
        }

        for (InjectionElement element : injectionElements) {
            element.inject(target);
        }
    }

    public List<InjectionElement> getInjectionElements() {
        return this.injectionElements;
    }
}
