package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.CupomDTO;
import br.com.unitins.a1.dto.CupomResponseDTO;

public interface CupomService {
        CupomResponseDTO create(CupomDTO dto);
        CupomResponseDTO update(CupomDTO dto, Long id);
        void delete(Long id);
        CupomResponseDTO findById(Long id);
        CupomResponseDTO findByCodigo(String codigo);
}
