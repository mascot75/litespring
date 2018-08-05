package org.litespring.context.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.BeanNameGenerator;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResourceLoader;
import org.litespring.core.type.classreading.MetadataReader;
import org.litespring.core.type.classreading.SimpleMetadataReader;
import org.litespring.stereotype.Component;
import org.litespring.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * @author Jack
 */
public class ClassPathBeanDefinitionScanner {

    private final BeanDefinitionRegistry registry;
    private PackageResourceLoader resourceLoader = new PackageResourceLoader();
   private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
    protected final Log logger = LogFactory.getLog(getClass());

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public Set<BeanDefinition> doScan(String packagesToSan) {
        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToSan, ",");
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = this.findCandidateComponents(basePackage);
            assert candidates != null;
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getID(), candidate);
            }
        }
        return beanDefinitions;
    }

    private Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Resource[] resources = this.resourceLoader.getResources(basePackage);
        for (Resource resource : resources) {
            try {
                MetadataReader metadataReader = new SimpleMetadataReader(resource);
                if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
                    ScannedGenericBeanDefinition definition = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                    String baseName = this.beanNameGenerator.generateBeanName(definition, this.registry);
                    definition.setId(baseName);
                    candidates.add(definition);
                }
            } catch (Throwable ex) {
                throw new BeanDefinitionStoreException("Failed to read candidate component class: " + resource, ex);
            }
        }
        return candidates;
    }
}
