package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public abstract class ItemDTO<T extends Item> {
    @NotBlank
    public final String nome;
    @NotBlank
    public final String descricao;
    @Positive
    public final Double preco;
    @Positive
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
