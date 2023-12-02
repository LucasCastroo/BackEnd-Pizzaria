package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String logradouro,
        String bairro,
        String cidade,
        String cep
) {
    public static EnderecoResponseDTO valueOf(Endereco endereco){
        try {
            return new EnderecoResponseDTO(
                    endereco.getId(),
                    endereco.getLogradouro(),
                    endereco.getBairro(),
                    endereco.getCidade(),
                    endereco.getCep()
            );
        }catch (NullPointerException e){
            return null;
        }
    }
}
