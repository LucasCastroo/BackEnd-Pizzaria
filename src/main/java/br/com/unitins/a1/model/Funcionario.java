package br.com.unitins.a1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
public class Funcionario extends DefaultEntity{
    private String nome;
    @Column(length = 14)
    private String cpf;
    private String email;
    private String senha;
    private LocalDate nascimento;
    @Enumerated(EnumType.STRING)
    private NivelAcesso tipoAcesso;

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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
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
