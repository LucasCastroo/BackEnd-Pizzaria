package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Funcionario;
import br.unitins.tp1.pizzaria.model.NivelAcesso;

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
