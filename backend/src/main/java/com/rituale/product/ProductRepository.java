package com.rituale.product;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"category", "images"})
    List<Product> findByActiveTrueOrderByCreatedAtDesc();

    @EntityGraph(attributePaths = {"category", "images"})
    Optional<Product> findBySlugAndActiveTrue(String slug);

    @EntityGraph(attributePaths = {"category", "images"})
    List<Product> findAllByOrderByCreatedAtDesc();
}
