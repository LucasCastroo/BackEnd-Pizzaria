package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Cupom;
import br.unitins.tp1.pizzaria.model.FormaPagamento;
import br.unitins.tp1.pizzaria.model.ItemPedido;
import br.unitins.tp1.pizzaria.model.Pedido;

import java.util.List;

public record PedidoResponseDTO(
        Long id,
        ClienteResponseDTO cliente,
        List<ItemPedido> items,
        FormaPagamento formaPagamento,
        Cupom cupom,
        Double total,
        List<StatusPedidoResponseDTO> historicoStatus,
        EnderecoResponseDTO endereco
        ) {

    public static PedidoResponseDTO from(Pedido pedido){
        return new PedidoResponseDTO(
                pedido.getId(),
                ClienteResponseDTO.valueOf(pedido.getCliente()),
                pedido.getItems(),
                pedido.getFormaPagamento(),
                pedido.getCupom(),
                pedido.getItems().stream().map(i-> i.getPreco() * i.getQuant()).reduce(0.0, Double::sum) - (pedido.getCupom() != null ? pedido.getCupom().getDesconto()  : 0),
                pedido.getStatus().stream().map(StatusPedidoResponseDTO::from).toList(),
                EnderecoResponseDTO.valueOf(pedido.getEndereco().getEndereco())
        );
    }
}
