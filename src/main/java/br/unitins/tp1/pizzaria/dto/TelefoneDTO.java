package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.validation.Telefone;
import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO(
        @Telefone @NotBlank String telefone
) {

}
