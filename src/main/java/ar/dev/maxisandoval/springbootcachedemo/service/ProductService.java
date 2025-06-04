package ar.dev.maxisandoval.springbootcachedemo.service;

import ar.dev.maxisandoval.springbootcachedemo.model.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable("products")
    public Product getProductById(Long id) {
        simulateSlowService();
        return new Product(id, "Producto " + id);
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}