package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Pizza;
import br.com.unitins.a1.model.TamanhoPizza;

public record PizzaDTO(
        String nome,
        String descricao,
        Double preco,
        Integer kCal,
        TamanhoPizza tamanhoPizza,
        String ingredientes,
        Integer tempoDePreparo
) {
    public Pizza valueOf(){
        Pizza pizza = new Pizza();
        pizza.setTamanhoPizza(tamanhoPizza());
        pizza.setIngredientes(ingredientes());
        pizza.setNome(nome());
        pizza.setDescricao(descricao());
        pizza.setkCal(kCal());
        pizza.setTempoDePreparo(tempoDePreparo());
        pizza.setPreco(preco());
        return pizza;
    }
}
