package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.ItemDTO;
import br.unitins.tp1.pizzaria.dto.ItemResponseDTO;

public interface ItemService<DTO extends ItemDTO, RDTO extends ItemResponseDTO> {

    RDTO create(DTO dto);

    RDTO update(Long id, DTO dto);

    RDTO updateImage(Long id, String nomeImagem);

    void delete(Long id);

    RDTO findById(Long id);
}
