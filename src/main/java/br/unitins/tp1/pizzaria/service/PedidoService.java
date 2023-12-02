package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.PedidoDTO;
import br.unitins.tp1.pizzaria.dto.PedidoResponseDTO;
import br.unitins.tp1.pizzaria.dto.StatusPedidoDTO;

import java.util.List;

public interface PedidoService {
    PedidoResponseDTO create(PedidoDTO dto, Long idCliente);

    PedidoResponseDTO update(PedidoDTO dto, Long id);

    PedidoResponseDTO updateStatus(StatusPedidoDTO dto, Long id);

    void delete(Long id);

    PedidoResponseDTO findById(Long id);
    List<PedidoResponseDTO> findByClienteId(Long idCliente);
}
