package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Item;

public abstract class ItemResponseDTO<T extends Item> {
    private final Long id;
    private final String nome;
    private final String descricao;
    private final Double preco;
    private final Integer kCal;
    private final String nomeImagem;

    protected ItemResponseDTO(Long id, String nome, String descricao, Double preco, Integer kCal, String nomeImagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.kCal = kCal;
        this.nomeImagem = nomeImagem;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getkCal() {
        return kCal;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public Long getId() {
        return id;
    }
}
