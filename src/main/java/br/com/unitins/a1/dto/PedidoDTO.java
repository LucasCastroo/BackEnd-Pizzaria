package br.com.unitins.a1.dto;


import br.com.unitins.a1.model.FormaPagamento;
import br.com.unitins.a1.model.Pedido;

import java.util.List;

public record PedidoDTO(
    List<ItemPedidoDTO> items,
    String cupom,
    Long idEndereco,
    FormaPagamento formaPagamento
) {
}
