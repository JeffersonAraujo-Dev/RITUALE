package com.rituale.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @Column(length = 255)
    private String altText;

    @Column(nullable = false)
    private Integer position = 0;

    protected ProductImage() {}

    public ProductImage(Product product, String url, String altText, Integer position) {
        this.product = product;
        this.url = url;
        this.altText = altText;
        this.position = position;
    }

    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public String getUrl() { return url; }
    public String getAltText() { return altText; }
    public Integer getPosition() { return position; }

    public void setProduct(Product product) { this.product = product; }
    public void setUrl(String url) { this.url = url; }
    public void setAltText(String altText) { this.altText = altText; }
    public void setPosition(Integer position) { this.position = position; }
}
