package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Item;

public abstract class ItemDTO<T extends Item> {
    public final String nome;
    public final String descricao;
    public final Double preco;
    public final Integer kCal;


    public ItemDTO(String nome, String descricao, Double preco, Integer kCal) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.kCal = kCal;
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

    public abstract T valueOf();
}
