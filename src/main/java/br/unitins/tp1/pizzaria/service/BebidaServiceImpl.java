package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.BebidaDTO;
import br.unitins.tp1.pizzaria.dto.BebidaResponseDTO;
import br.unitins.tp1.pizzaria.model.Bebida;
import br.unitins.tp1.pizzaria.repository.BebidaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BebidaServiceImpl implements ItemService<BebidaDTO, BebidaResponseDTO> {
    @Inject
    BebidaRepository repository;


    @Override
    @Transactional
    public BebidaResponseDTO create(BebidaDTO dto) {
        Bebida bebida = dto.valueOf();
        repository.persist(bebida);
        return BebidaResponseDTO.from(bebida);
    }

    @Override
    @Transactional
    public BebidaResponseDTO update(Long id, BebidaDTO dto) {
        Bebida bebida = repository.findById(id);
        if (dto.getDescricao() != null) bebida.setDescricao(dto.getDescricao());
        if (dto.getkCal() != null) bebida.setkCal(dto.getkCal());
        if (dto.getNome() != null) bebida.setNome(dto.getNome());
        if (dto.getPreco() != null) bebida.setPreco(dto.getPreco());
        if (dto.getMl() != null) bebida.setMl(dto.getMl());
        repository.persistAndFlush(bebida);
        return BebidaResponseDTO.from(bebida);
    }

    @Override
    @Transactional
    public BebidaResponseDTO updateImage(Long id, String nomeImagem) {
        Bebida item = repository.findById(id);
        item.setNomeImagem(nomeImagem);
        repository.persistAndFlush(item);
        return BebidaResponseDTO.from(item);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BebidaResponseDTO findById(Long id) {
        return BebidaResponseDTO.from(repository.findById(id));
    }

}
