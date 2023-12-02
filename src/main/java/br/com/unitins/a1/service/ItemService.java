package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.ItemDTO;
import br.com.unitins.a1.dto.ItemResponseDTO;

public interface ItemService<DTO extends ItemDTO, RDTO extends ItemResponseDTO> {

    RDTO create(DTO dto);

    RDTO update(Long id, DTO dto);

    RDTO updateImage(Long id, String nomeImagem);

    void delete(Long id);

    RDTO findById(Long id);
}
