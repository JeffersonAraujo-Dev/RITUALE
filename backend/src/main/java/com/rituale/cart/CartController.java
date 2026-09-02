package com.rituale.cart;

import com.rituale.product.Product;
import com.rituale.product.ProductRepository;
import com.rituale.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartController(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public CartSummary getCart(@AuthenticationPrincipal User user) {
        Cart cart = cartRepository.findByUserId(user.getId()).orElseGet(() -> cartRepository.save(new Cart(user)));
        return CartSummary.from(cart);
    }

    @PostMapping("/items")
    public ResponseEntity<CartSummary> addItem(@AuthenticationPrincipal User user,
                                              @RequestBody CartItemRequest request) {
        Cart cart = cartRepository.findByUserId(user.getId()).orElseGet(() -> cartRepository.save(new Cart(user)));
        Product product = productRepository.findById(request.productId())
            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (product.getStock() < request.quantity()) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto selecionado");
        }

        cart.getItems().stream()
            .filter(item -> item.getProduct().getId().equals(product.getId()))
            .findFirst()
            .ifPresentOrElse(item -> item.setQuantity(item.getQuantity() + request.quantity()),
                () -> cart.getItems().add(new CartItem(cart, product, request.quantity())));

        cart.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(CartSummary.from(cartRepository.save(cart)));
    }

    @PutMapping("/items/{productId}")
    public ResponseEntity<CartSummary> updateItem(@AuthenticationPrincipal User user,
                                                @PathVariable Long productId,
                                                @RequestBody CartItemRequest request) {
        Cart cart = cartRepository.findByUserId(user.getId())
            .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado"));

        CartItem item = cart.getItems().stream()
            .filter(entry -> entry.getProduct().getId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado no carrinho"));

        item.setQuantity(request.quantity());
        cart.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(CartSummary.from(cartRepository.save(cart)));
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItem(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        Cart cart = cartRepository.findByUserId(user.getId())
            .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }

    public record CartItemRequest(Long productId, Integer quantity) {}

    public record CartSummary(Long id, List<CartLine> items, java.math.BigDecimal total) {
        public static CartSummary from(Cart cart) {
            List<CartLine> lines = cart.getItems().stream()
                .map(item -> new CartLine(item.getProduct().getId(), item.getProduct().getName(), item.getQuantity(), item.getProduct().getPrice()))
                .toList();

            java.math.BigDecimal total = lines.stream()
                .map(line -> line.unitPrice().multiply(java.math.BigDecimal.valueOf(line.quantity())))
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

            return new CartSummary(cart.getId(), lines, total);
        }
    }

    public record CartLine(Long productId, String name, Integer quantity, java.math.BigDecimal unitPrice) {}
}
