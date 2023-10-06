package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.NivelAcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

public class FuncionarioDTO {
    @NotBlank(message = "Campo nome não pode ser nulo!")
    private final String nome;
    @NotBlank(message = "Campo cpf não pode ser nulo!")
    private final String cpf;
    @NotBlank(message = "Campo email não pode ser nulo!")
    private final String email;
    @NotBlank(message = "Campo senha não pode ser nulo!")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracters!")
    private final String senha;
    private final LocalDate nascimento;
    private final NivelAcesso tipoAcesso;

    public FuncionarioDTO(String nome, String cpf, String email, String senha, LocalDate nascimento, NivelAcesso tipoAcesso) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.tipoAcesso = tipoAcesso;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public NivelAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncionarioDTO that = (FuncionarioDTO) o;
        return Objects.equals(nome, that.nome) && Objects.equals(cpf, that.cpf) && Objects.equals(email, that.email) && Objects.equals(senha, that.senha) && Objects.equals(nascimento, that.nascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, email, senha, nascimento);
    }

}
