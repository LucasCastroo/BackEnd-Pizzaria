package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Cliente;
import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        String nascimento,
        List<EnderecoDTO> enderecos
) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        try{
            return new ClienteResponseDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getEmail(),
                    cliente.getTelefone(),
                    cliente.getNascimento(),
                    cliente.getEnderecos().stream().map(e -> new EnderecoDTO(e.getLogradouro(), e.getBairro(), e.getCidade(), e.getCep())).toList()
            );
        }catch (NullPointerException e){
            return null;
        }

    }
}
