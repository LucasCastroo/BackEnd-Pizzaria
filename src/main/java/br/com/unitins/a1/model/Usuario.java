package br.com.unitins.a1.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.validator.constraints.br.CPF;

@MappedSuperclass
public abstract class Usuario extends DefaultEntity {
    private String nome;
    @Column(length = 14)
    @CPF
    private String cpf;
    private String email;
    private String senha;
    private String nascimento;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}
