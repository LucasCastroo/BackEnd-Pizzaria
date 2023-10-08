package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Status;
import br.com.unitins.a1.model.StatusPedido;

import java.time.LocalDateTime;

public record StatusPedidoResponseDTO(
        LocalDateTime data,
        Status status
) {

    public static StatusPedidoResponseDTO from(StatusPedido statusPedido) {
        return new StatusPedidoResponseDTO(statusPedido.getHorario(), statusPedido.getStatus());
    }
}
