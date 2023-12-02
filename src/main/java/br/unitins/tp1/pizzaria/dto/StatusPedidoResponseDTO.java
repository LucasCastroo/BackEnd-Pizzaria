package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Status;
import br.unitins.tp1.pizzaria.model.StatusPedido;

import java.time.LocalDateTime;

public record StatusPedidoResponseDTO(
        LocalDateTime data,
        Status status
) {

    public static StatusPedidoResponseDTO from(StatusPedido statusPedido) {
        return new StatusPedidoResponseDTO(statusPedido.getHorario(), statusPedido.getStatus());
    }
}
