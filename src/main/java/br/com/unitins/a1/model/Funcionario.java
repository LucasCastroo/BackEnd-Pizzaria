package br.com.unitins.a1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Funcionario extends Usuario {
    public static final String ROLE = "FUNCIONARIO";
    @Enumerated(EnumType.STRING)
    private NivelAcesso tipoAcesso;

    public Funcionario(String nome, String cpf, String email, String senha, String nascimento, NivelAcesso tipoAcesso) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setSenha(senha);
        this.setNascimento(nascimento);
        this.tipoAcesso = tipoAcesso;
    }

    public Funcionario() {

    }

    public NivelAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(NivelAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
}
