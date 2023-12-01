package br.com.unitins.a1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class ClienteDTO {
    @NotBlank(message = "Campo nome não pode ser nulo!")
    private final String nome;
    @NotBlank(message = "Campo cpf não pode ser nulo!")
    @Size(min = 11, max = 14, message = "O campo cpf tem que ter no mínimo 11 digitos!")
    private final String cpf;
    @NotBlank(message = "Campo email não pode ser nulo!")
    private final String email;
    @NotBlank(message = "Campo senha não pode ser nulo!")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracters!")
    private final String senha;
    @NotBlank(message = "Campo telefone não pode ser nulo!")
    @Size(min = 11, message = "Campo telefone deve conter no mínimo 11 caracters!")
    private final String telefone;
    private final String nascimento;
    private final List<EnderecoDTO> enderecos;

    public ClienteDTO(String nome, String cpf, String email, String senha, String telefone, String nascimento, List<EnderecoDTO> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.enderecos = enderecos;
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

    public String getSenha() { return senha; }

    public String getTelefone() {
        return telefone;
    }

    public String getNascimento() {
        return nascimento;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDTO that = (ClienteDTO) o;
        return Objects.equals(nome, that.nome) && Objects.equals(cpf, that.cpf) && Objects.equals(email, that.email) && Objects.equals(senha, that.senha) && Objects.equals(telefone, that.telefone) && Objects.equals(nascimento, that.nascimento) && Objects.equals(enderecos, that.enderecos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, email, senha, telefone, nascimento, enderecos);
    }
}
