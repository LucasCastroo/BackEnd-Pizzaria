package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Cupom;

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
