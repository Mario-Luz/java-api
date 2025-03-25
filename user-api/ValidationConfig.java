package com.mario.java.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Validated
public class ValidationConfig implements WebMvcConfigurer {
    // Configurações adicionais de validação podem ser adicionadas aqui
}