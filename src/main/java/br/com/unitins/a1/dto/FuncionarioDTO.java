package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.NivelAcesso;

import java.time.LocalDate;

public class FuncionarioDTO extends UsuarioDTO {
    private final NivelAcesso tipoAcesso;

    public FuncionarioDTO(String nome, String cpf, String email, String senha, LocalDate nascimento, NivelAcesso tipoAcesso) {
        super(nome, cpf, email, senha, nascimento);
        this.tipoAcesso = tipoAcesso;
    }


    public NivelAcesso getTipoAcesso() {
        return tipoAcesso;
    }


}
