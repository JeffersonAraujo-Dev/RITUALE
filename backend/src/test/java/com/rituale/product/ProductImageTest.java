package com.rituale.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductImageTest {

    @Test
    void shouldReturnFirstImageUrl() {
        Product product = new Product();
        product.setName("Bliss");
        product.setSlug("bliss");

        ProductImage image = new ProductImage();
        image.setUrl("https://example.com/bliss.webp");
        product.getImages().add(image);

        assertEquals("https://example.com/bliss.webp", product.getImageUrl());
    }
}
