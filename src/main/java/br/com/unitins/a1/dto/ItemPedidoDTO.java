package br.com.unitins.a1.dto;

public record ItemPedidoDTO(
        Integer quantidade,
        Long idItem,
        TipoItem tipo

) {
}
