package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Cupom;
import br.com.unitins.a1.model.FormaPagamento;
import br.com.unitins.a1.model.ItemPedido;
import br.com.unitins.a1.model.Pedido;

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
        try {
            return new PedidoResponseDTO(
                    pedido.getId(),
                    ClienteResponseDTO.valueOf(pedido.getCliente()),
                    pedido.getItems(),
                    pedido.getFormaPagamento(),
                    pedido.getCupom(),
                    pedido.getTotal(),
                    pedido.getStatus().stream().map(StatusPedidoResponseDTO::from).toList(),
                    EnderecoResponseDTO.valueOf(pedido.getEndereco().getEndereco())
            );
        } catch (NullPointerException e) {
            return null;
        }
    }
}
