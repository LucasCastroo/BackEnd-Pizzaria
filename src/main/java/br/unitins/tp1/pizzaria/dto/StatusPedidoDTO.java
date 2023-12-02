package br.unitins.tp1.pizzaria.dto;

import br.unitins.tp1.pizzaria.model.Status;
import br.unitins.tp1.pizzaria.model.StatusPedido;

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
