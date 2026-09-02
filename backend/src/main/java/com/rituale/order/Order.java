package com.rituale.order;

import com.rituale.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerPhone;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(name = "whatsapp_message", nullable = false, length = 4000)
    private String whatsappMessage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected Order() {}

    public Order(User user, String customerName, String customerPhone, BigDecimal total, String whatsappMessage) {
        this.user = user;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.total = total;
        this.whatsappMessage = whatsappMessage;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public BigDecimal getTotal() { return total; }
    public String getWhatsappMessage() { return whatsappMessage; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setStatus(OrderStatus status) { this.status = status; }
}
