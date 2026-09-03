package com.rituale.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/index").setViewName("forward:/index.html");
        registry.addViewController("/login").setViewName("forward:/login.html");
        registry.addViewController("/cadastro").setViewName("forward:/cadastro.html");
        registry.addViewController("/produtos").setViewName("forward:/produtos.html");
        registry.addViewController("/produto").setViewName("forward:/produto.html");
        registry.addViewController("/carrinho").setViewName("forward:/carrinho.html");
        registry.addViewController("/favoritos").setViewName("forward:/favoritos.html");
    }
}
