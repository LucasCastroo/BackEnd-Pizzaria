package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Bebida;

public record BebidaDTO(
        String nome,
        String descricao,
        Double preco,
        Integer kCal,
        Integer ml
) {
    public Bebida valueOf(){
        Bebida bebida = new Bebida();
        bebida.setNome(nome());
        bebida.setDescricao(descricao());
        bebida.setkCal(kCal());
        bebida.setMl(ml());
        bebida.setPreco(preco());
        return bebida;
    }
}
