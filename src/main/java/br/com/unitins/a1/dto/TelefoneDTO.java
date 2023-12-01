package br.com.unitins.a1.dto;

import br.com.unitins.a1.validation.Telefone;
import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO(
        @Telefone @NotBlank String telefone
) {

}
