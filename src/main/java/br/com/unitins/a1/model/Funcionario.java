package br.com.unitins.a1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Funcionario extends DefaultEntity{
    private String nome;
    @Column(length = 14)
    private String cpf;
    private String email;
    private String senha;
    private String nascimento;
    @Enumerated(EnumType.STRING)
    private NivelAcesso tipoAcesso;

    public Funcionario(String nome, String cpf, String email, String senha, String nascimento, NivelAcesso tipoAcesso) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.tipoAcesso = tipoAcesso;
    }

    public Funcionario() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(NivelAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
}
