package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.PedidoDTO;
import br.com.unitins.a1.dto.PedidoResponseDTO;
import br.com.unitins.a1.dto.StatusPedidoDTO;

import java.util.List;

public interface PedidoService {
    PedidoResponseDTO create(PedidoDTO dto, Long idCliente);

    PedidoResponseDTO update(PedidoDTO dto, Long id);

    PedidoResponseDTO updateStatus(StatusPedidoDTO dto, Long id);

    void delete(Long id);

    PedidoResponseDTO findById(Long id);
    List<PedidoResponseDTO> findByClienteId(Long idCliente);
}
