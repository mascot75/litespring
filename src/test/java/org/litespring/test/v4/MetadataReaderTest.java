package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.classreading.MetadataReader;
import org.litespring.core.type.classreading.SimpleMetadataReader;
import org.litespring.stereotype.Component;

import java.io.IOException;

public class MetadataReaderTest {
    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
        MetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata metadata = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();
        Assert.assertTrue(metadata.hasAnnotation(annotation));
        AnnotationAttributes attributes = metadata.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

        Assert.assertFalse(metadata.isAbstract());
        Assert.assertFalse(metadata.isFinal());
        Assert.assertEquals("org.litespring.service.v4.PetStoreService", metadata.getClassName());

    }
}
