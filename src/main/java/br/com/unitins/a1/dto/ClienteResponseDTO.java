package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Cliente;
import br.com.unitins.a1.model.Endereco;

import java.time.LocalDate;
import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate nascimento,
        List<Endereco> enderecos
) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getNascimento(),
                cliente.getEnderecos()
        );
    }
}
