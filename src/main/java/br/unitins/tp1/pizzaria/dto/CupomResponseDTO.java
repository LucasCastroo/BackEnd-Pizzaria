package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Cupom;

public record CupomResponseDTO(
        Long id,
        String codigo,
        Double desconto
) {
    public static CupomResponseDTO from(Cupom cupom){
        try {
            return new CupomResponseDTO(
                    cupom.getId(),
                    cupom.getCodigo(),
                    cupom.getDesconto()
            );
        }catch (NullPointerException e){
            return null;
        }
    }
}
