package br.com.unitins.a1.dto;

import br.com.unitins.a1.model.Status;
import br.com.unitins.a1.model.StatusPedido;

import java.time.LocalDateTime;

public record StatusPedidoDTO(
        Status status
) {
    public StatusPedido valueOf() {
        StatusPedido statusPedido = new StatusPedido();
        statusPedido.setStatus(status());
        statusPedido.setHorario(LocalDateTime.now());
        return statusPedido;
    }
}
