package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Cupom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CupomDTO(
        @NotBlank
        String codigo,
        @NotNull
        @Positive
        Double desconto
) {

    public Cupom valueOf(){
        Cupom cupom = new Cupom();
        cupom.setCodigo(codigo());
        cupom.setDesconto(desconto());
        return cupom;
    }
}
