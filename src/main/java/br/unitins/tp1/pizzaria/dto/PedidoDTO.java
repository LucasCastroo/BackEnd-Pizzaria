package br.unitins.tp1.pizzaria.dto;


import br.unitins.tp1.pizzaria.model.FormaPagamento;

import java.util.List;

public record PedidoDTO(
    List<ItemPedidoDTO> items,
    String cupom,
    Long idEndereco,
    FormaPagamento formaPagamento
) {
}
