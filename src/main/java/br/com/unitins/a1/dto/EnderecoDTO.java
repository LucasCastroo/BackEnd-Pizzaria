package br.com.unitins.a1.dto;

public class EnderecoDTO {
    private final String logradouro;
    private final String bairro;
    private final String cidade;
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
