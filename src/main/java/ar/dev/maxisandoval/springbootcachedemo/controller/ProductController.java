package ar.dev.maxisandoval.springbootcachedemo.controller;

import ar.dev.maxisandoval.springbootcachedemo.model.Product;
import ar.dev.maxisandoval.springbootcachedemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}