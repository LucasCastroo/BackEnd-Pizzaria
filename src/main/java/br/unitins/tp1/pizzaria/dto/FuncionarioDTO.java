package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.NivelAcesso;

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
