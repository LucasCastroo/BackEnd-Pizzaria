package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Endereco;

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
