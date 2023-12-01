package br.com.unitins.a1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record NascimentoDTO(
        @NotBlank @Past LocalDate nascimento
) {
}
