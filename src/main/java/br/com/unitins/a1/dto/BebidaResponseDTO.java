package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Bebida;

public class BebidaResponseDTO extends ItemResponseDTO<Bebida> {
    private final Integer ml;

    public BebidaResponseDTO(String nome, String descricao, Double preco, Integer kCal, String nomeImagem, Integer ml) {
        super(nome, descricao, preco, kCal, nomeImagem);
        this.ml = ml;
    }


    public static BebidaResponseDTO from(Bebida item) {
        try {
            return new BebidaResponseDTO(
                    item.getNome(),
                    item.getDescricao(),
                    item.getPreco(),
                    item.getkCal(),
                    item.getNomeImagem(),
                    item.getMl()
            );
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Integer getMl() {
        return ml;
    }
}
