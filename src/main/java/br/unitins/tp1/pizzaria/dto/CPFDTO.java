package br.unitins.tp1.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record CPFDTO(
        @NotBlank @CPF String cpf
) {
}
