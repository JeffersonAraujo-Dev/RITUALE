package com.rituale.product;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/public/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> findAll() {
        return repository.findByActiveTrueOrderByCreatedAtDesc();
    }

    @GetMapping("/{slug}")
    public Product findBySlug(@PathVariable String slug) {
        return repository.findBySlugAndActiveTrue(slug)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
