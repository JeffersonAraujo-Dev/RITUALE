package com.rituale.favorite;

import com.rituale.product.Product;
import com.rituale.product.ProductRepository;
import com.rituale.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;

    public FavoriteController(FavoriteRepository favoriteRepository, ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> list(@AuthenticationPrincipal User user) {
        return favoriteRepository.findByUserId(user.getId()).stream()
            .map(Favorite::getProduct)
            .toList();
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> add(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (!favoriteRepository.existsByUserIdAndProductId(user.getId(), productId)) {
            favoriteRepository.save(new Favorite(user, product));
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> remove(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        favoriteRepository.findByUserIdAndProductId(user.getId(), productId)
            .ifPresent(favoriteRepository::delete);
        return ResponseEntity.noContent().build();
    }
}
