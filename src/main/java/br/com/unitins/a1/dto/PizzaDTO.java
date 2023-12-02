package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Pizza;
import br.com.unitins.a1.model.TamanhoPizza;


public class PizzaDTO extends ItemDTO<Pizza> {

    private final TamanhoPizza tamanhoPizza;
    private final String ingredientes;
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

