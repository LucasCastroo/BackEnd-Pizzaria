package br.unitins.tp1.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;

public record NomeDTO(
        @NotBlank String nome
) {
}
