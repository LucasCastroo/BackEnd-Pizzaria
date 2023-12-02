package br.com.unitins.a1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record NascimentoDTO(
        @NotNull @Past LocalDate nascimento
) {
}
