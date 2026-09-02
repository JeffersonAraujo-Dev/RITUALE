package com.rituale.order;

import com.rituale.cart.Cart;
import com.rituale.cart.CartRepository;
import com.rituale.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Value("${app.whatsapp.phone:5511999999999}")
    private String whatsappPhone;

    public OrderController(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public List<Order> list(@AuthenticationPrincipal User user) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
    }

    @PostMapping
    public ResponseEntity<Order> checkout(@AuthenticationPrincipal User user,
                                         @RequestBody CheckoutRequest request) {
        Cart cart = cartRepository.findByUserId(user.getId())
            .orElseThrow(() -> new IllegalArgumentException("Carrinho vazio"));

        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Carrinho vazio");
        }

        BigDecimal total = cart.getItems().stream()
            .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        String message = buildWhatsAppMessage(user, request, cart, total);
        Order order = new Order(user, request.customerName(), request.customerPhone(), total, message);
        Order saved = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return ResponseEntity.ok(saved);
    }

    private String buildWhatsAppMessage(User user, CheckoutRequest request, Cart cart, BigDecimal total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Olá, meu nome é ").append(request.customerName()).append(".\n");
        sb.append("Quero confirmar o pedido da Rituale.\n");
        sb.append("Cliente: ").append(user.getName()).append("\n");
        sb.append("Telefone: ").append(request.customerPhone()).append("\n\n");
        sb.append("Itens:\n");

        cart.getItems().forEach(item -> sb.append("- ")
            .append(item.getProduct().getName())
            .append(" x")
            .append(item.getQuantity())
            .append("\n"));

        sb.append("\nTotal: R$ ").append(total).append("\n");
        sb.append("Observações: ").append(request.note() == null ? "Nenhuma" : request.note()).append("\n");
        sb.append("Link para WhatsApp: https://wa.me/").append(whatsappPhone).append("?text=")
            .append(java.net.URLEncoder.encode(messageToCustomer(request.customerName(), total), java.nio.charset.StandardCharsets.UTF_8));

        return sb.toString();
    }

    private String messageToCustomer(String customerName, BigDecimal total) {
        return "Olá! Quero finalizar meu pedido de Rituale. Cliente: " + customerName + ". Valor total: R$ " + total;
    }

    public record CheckoutRequest(String customerName, String customerPhone, String note) {}
}
