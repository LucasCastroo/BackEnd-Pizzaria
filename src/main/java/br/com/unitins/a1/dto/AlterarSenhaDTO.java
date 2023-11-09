package br.com.unitins.a1.dto;

import jakarta.validation.constraints.Size;

public record AlterarSenhaDTO(
        @Size(min = 8)
        String antigaSenha,
        @Size(min = 8)
        String novaSenha
) {

}
