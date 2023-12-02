package br.unitins.tp1.pizzaria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @Email
        String email,
        @Size(min = 8)
        String senha
) {
}
