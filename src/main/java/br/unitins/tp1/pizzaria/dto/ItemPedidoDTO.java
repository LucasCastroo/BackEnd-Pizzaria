package br.unitins.tp1.pizzaria.dto;

public record ItemPedidoDTO(
        Integer quantidade,
        Long idItem,
        TipoItem tipo

) {
}
