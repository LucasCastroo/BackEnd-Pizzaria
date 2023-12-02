package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Bebida;

public class BebidaResponseDTO extends ItemResponseDTO<Bebida> {
    private final Integer ml;

    public BebidaResponseDTO(Long id, String nome, String descricao, Double preco, Integer kCal, String nomeImagem, Integer ml) {
        super(id, nome, descricao, preco, kCal, nomeImagem);
        this.ml = ml;
    }


    public static BebidaResponseDTO from(Bebida item) {
        try {
            return new BebidaResponseDTO(
                    item.getId(),
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
