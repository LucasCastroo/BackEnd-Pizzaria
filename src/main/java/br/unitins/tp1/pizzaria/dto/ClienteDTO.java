package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.validation.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class ClienteDTO extends UsuarioDTO {
    @NotBlank(message = "Campo telefone não pode ser nulo!")
    @Size(min = 11, message = "Campo telefone deve conter no mínimo 11 caracters!")
    @Telefone
    private final String telefone;
    private final List<EnderecoDTO> enderecos;

    public ClienteDTO(String nome, String cpf, String email, String senha, String telefone, LocalDate nascimento, List<EnderecoDTO> enderecos) {
        super(nome, cpf, email, senha, nascimento);
        this.telefone = telefone;
        this.enderecos = enderecos;
    }


    public String getTelefone() {
        return telefone;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }


}
