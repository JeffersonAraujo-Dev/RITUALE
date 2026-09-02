package com.rituale.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail obrigatório")
    String email,

    @NotBlank(message = "Senha obrigatória")
    String password
) {}
