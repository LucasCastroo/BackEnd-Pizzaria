package br.com.unitins.a1.dto;

import jakarta.validation.constraints.NotBlank;

public record NomeDTO(
        @NotBlank String nome
) {
}
