package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.PizzaDTO;
import br.unitins.tp1.pizzaria.dto.PizzaResponseDTO;
import br.unitins.tp1.pizzaria.model.Pizza;
import br.unitins.tp1.pizzaria.repository.PizzaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PizzaServiceImpl implements ItemService<PizzaDTO, PizzaResponseDTO> {

    @Inject
    PizzaRepository repository;

    @Override
    @Transactional
    public PizzaResponseDTO create(PizzaDTO dto) {
        Pizza pizza = dto.valueOf();
        repository.persist(pizza);
        return PizzaResponseDTO.from(pizza);
    }

    @Override
    @Transactional
    public PizzaResponseDTO update(Long id, PizzaDTO dto) {
        Pizza pizza = repository.findById(id);
        if (dto.getDescricao() != null) pizza.setDescricao(dto.getDescricao());
        if (dto.getkCal() != null) pizza.setkCal(dto.getkCal());
        if (dto.getNome() != null) pizza.setNome(dto.getNome());
        if (dto.getPreco() != null) pizza.setPreco(dto.getPreco());
        if (dto.getIngredientes() != null) pizza.setIngredientes(dto.getIngredientes());
        if (dto.getTamanhoPizza() != null) pizza.setTamanhoPizza(dto.getTamanhoPizza());
        if (dto.getTempoDePreparo() != null) pizza.setTempoDePreparo(dto.getTempoDePreparo());
        repository.persistAndFlush(pizza);
        return PizzaResponseDTO.from(pizza);
    }

    @Override
    @Transactional
    public PizzaResponseDTO updateImage(Long id, String nomeImagem) {
        Pizza item = repository.findById(id);
        item.setNomeImagem(nomeImagem);
        repository.persistAndFlush(item);
        return PizzaResponseDTO.from(item);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PizzaResponseDTO findById(Long id) {
        return PizzaResponseDTO.from(repository.findById(id));
    }
}
