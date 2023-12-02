package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.CupomDTO;
import br.unitins.tp1.pizzaria.dto.CupomResponseDTO;
import br.unitins.tp1.pizzaria.model.Cupom;
import br.unitins.tp1.pizzaria.repository.CupomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CupomServiceImpl implements CupomService{
    @Inject
    CupomRepository repository;

    @Override
    @Transactional
    public CupomResponseDTO create(CupomDTO dto) {
        Cupom cupom = dto.valueOf();
        repository.persist(cupom);
        return CupomResponseDTO.from(cupom);
    }

    @Transactional
    @Override
    public CupomResponseDTO update(CupomDTO dto, Long id) {
        Cupom cupom = repository.findById(id);
        if (dto.codigo() != null) cupom.setCodigo(dto.codigo());
        if (dto.desconto() != null) cupom.setDesconto(dto.desconto());
        repository.persistAndFlush(cupom);
        return CupomResponseDTO.from(cupom);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CupomResponseDTO findById(Long id) {
        return CupomResponseDTO.from(repository.findById(id));
    }

    @Override
    public CupomResponseDTO findByCodigo(String codigo) {
        return CupomResponseDTO.from(repository.findByCodigo(codigo));
    }
}
