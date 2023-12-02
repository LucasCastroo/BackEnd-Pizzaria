package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Pizza;
import br.unitins.tp1.pizzaria.model.TamanhoPizza;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class PizzaDTO extends ItemDTO<Pizza> {
    @NotNull
    private final TamanhoPizza tamanhoPizza;
    @NotBlank
    private final String ingredientes;
    @NotNull
    @Positive
    private final Integer tempoDePreparo;

    public PizzaDTO(String nome, String descricao, Double preco, Integer kCal, TamanhoPizza tamanhoPizza, String ingredientes, Integer tempoDePreparo) {
        super(nome, descricao, preco, kCal);
        this.tamanhoPizza = tamanhoPizza;
        this.ingredientes = ingredientes;
        this.tempoDePreparo = tempoDePreparo;
    }

    public TamanhoPizza getTamanhoPizza() {
        return tamanhoPizza;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }


    @Override
    public Pizza valueOf() {
        Pizza pizza = new Pizza();
        pizza.setTamanhoPizza(getTamanhoPizza());
        pizza.setIngredientes(getIngredientes());
        pizza.setNome(getNome());
        pizza.setDescricao(getDescricao());
        pizza.setkCal(getkCal());
        pizza.setTempoDePreparo(getTempoDePreparo());
        pizza.setPreco(getPreco());
        return pizza;
    }
}

