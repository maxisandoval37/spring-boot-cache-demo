package ar.dev.maxisandoval.springbootcachedemo;

import ar.dev.maxisandoval.springbootcachedemo.model.Product;
import ar.dev.maxisandoval.springbootcachedemo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void clearCacheClearsMapAndCache() {
        Product p = new Product(1L, "Test");
        productService.saveProduct(p);
        // first call to populate cache
        productService.getProductById(1L);

        Cache cache = cacheManager.getCache("products");
        assertNotNull(cache, "Cache should exist");
        assertNotNull(cache.get(1L), "Cache entry should exist");

        productService.clearCache();

        assertNull(cache.get(1L), "Cache should be cleared");
        @SuppressWarnings("unchecked")
        ConcurrentHashMap<Long, Product> fakeDb = (ConcurrentHashMap<Long, Product>) ReflectionTestUtils.getField(productService, "fakeDb");
        assertNotNull(fakeDb, "fakeDb should not be null");
        assertTrue(fakeDb.isEmpty(), "Map should be cleared");
    }
}
