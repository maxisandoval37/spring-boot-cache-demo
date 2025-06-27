package ar.dev.maxisandoval.springbootcachedemo.service;

import ar.dev.maxisandoval.springbootcachedemo.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class ProductService {

    private final ConcurrentHashMap<Long, Product> fakeDb = new ConcurrentHashMap<>();

    @Cacheable("products")
    public Product getProductById(Long id) {
        simulateSlowService();
        return fakeDb.get(id);
    }

    // Guarda el producto y lo pone en caché
    @CachePut(value = "products", key = "#product.id")
    public Product saveProduct(Product product) {
        fakeDb.put(product.getId(), product);
        return product;
    }

    // Elimina un producto de la caché (solo uno)
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        fakeDb.remove(id);
    }

    // Elimina toda la caché (todos los productos)
    @CacheEvict(value = "products", allEntries = true)
    public void clearCache() {
        fakeDb.clear();
        log.info("Cache eliminada");
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Thread was interrupted", e);
        }
    }
}