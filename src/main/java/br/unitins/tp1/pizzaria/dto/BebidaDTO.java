package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Bebida;

public class BebidaDTO extends ItemDTO<Bebida> {
    private final Integer ml;

    public BebidaDTO(String nome, String descricao, Double preco, Integer kCal, Integer ml) {
        super(nome, descricao, preco, kCal);
        this.ml = ml;
    }


    @Override
    public Bebida valueOf() {
        Bebida bebida = new Bebida();
        bebida.setNome(getNome());
        bebida.setDescricao(getDescricao());
        bebida.setkCal(getkCal());
        bebida.setMl(getMl());
        bebida.setPreco(getPreco());
        return bebida;
    }

    public Integer getMl() {
        return ml;
    }
}
