package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Pizza;
import br.unitins.tp1.pizzaria.model.TamanhoPizza;

public class PizzaResponseDTO extends ItemResponseDTO<Pizza> {
    private final TamanhoPizza tamanhoPizza;
    private final String ingredientes;
    private final Integer tempoDePreparo;

    public PizzaResponseDTO(Long id, String nome, String descricao, Double preco, Integer kCal, String nomeImagem, TamanhoPizza tamanhoPizza, String ingredientes, Integer tempoDePreparo) {
        super(id, nome, descricao, preco, kCal, nomeImagem);
        this.tamanhoPizza = tamanhoPizza;
        this.ingredientes = ingredientes;
        this.tempoDePreparo = tempoDePreparo;
    }

    public static PizzaResponseDTO from(Pizza item) {
        try {
            return new PizzaResponseDTO(
                    item.getId(),
                    item.getNome(),
                    item.getDescricao(),
                    item.getPreco(),
                    item.getkCal(),
                    item.getNomeImagem(),
                    item.getTamanhoPizza(),
                    item.getIngredientes(),
                    item.getTempoDePreparo()
            );

        } catch (NullPointerException e) {
            return null;
        }
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
}
