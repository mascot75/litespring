package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.InputStream;

public class ResourceTest {
    @Test
    public void testClassPathResource() throws Exception {

        Resource resource = new ClassPathResource("petstore_v1.xml");

        try (InputStream is = resource.getInputStream()) {
            // 注意：这个测试其实并不充分！！
            Assert.assertNotNull(is);
        }

    }

    @Test
    public void testFileSystemResource() throws Exception {

		Resource resource = new FileSystemResource("D:src\\test\\resources\\petstore_v1.xml");

        try (InputStream is = resource.getInputStream()) {
            // 注意：这个测试其实并不充分！！
            Assert.assertNotNull(is);
        }

   }

}
