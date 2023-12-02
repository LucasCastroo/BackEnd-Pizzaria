package br.unitins.tp1.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EnderecoDTO {
    @NotBlank
    private final String logradouro;
    @NotBlank
    private final String bairro;
    @NotBlank
    private final String cidade;
    @Size(min = 8, max = 9, message = "Tamanho de CEP inválido")
    private final String cep;

    public EnderecoDTO(String logradouro, String bairro, String cidade, String cep) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }
}
