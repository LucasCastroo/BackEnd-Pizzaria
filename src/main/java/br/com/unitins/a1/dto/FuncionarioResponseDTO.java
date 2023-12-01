package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Funcionario;
import br.com.unitins.a1.model.NivelAcesso;

import java.time.LocalDate;

public record FuncionarioResponseDTO(
    Long id,
    String nome,
    String email,
    String cpf,
    LocalDate nascimento,
    NivelAcesso tipoAcesso
) {
    public static FuncionarioResponseDTO valueOf(Funcionario funcionario) {
        try {
            return new FuncionarioResponseDTO(
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getEmail(),
                    funcionario.getCpf(),
                    funcionario.getNascimento(),
                    funcionario.getTipoAcesso()
            );
        }catch (NullPointerException e){
            return null;
        }
    }
}
