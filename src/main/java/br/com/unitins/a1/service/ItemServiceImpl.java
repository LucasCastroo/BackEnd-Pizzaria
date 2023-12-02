package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.BebidaDTO;
import br.com.unitins.a1.dto.PizzaDTO;
import br.com.unitins.a1.model.Bebida;
import br.com.unitins.a1.model.Item;
import br.com.unitins.a1.model.Pizza;
import br.com.unitins.a1.repository.BebidaRepository;
import br.com.unitins.a1.repository.PizzaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ItemServiceImpl implements ItemService{
    @Inject
    PizzaRepository pizzaRepository;

    @Inject
    BebidaRepository bebidaRepository;

    @Override
    @Transactional
    public Pizza createPizza(PizzaDTO dto) {
        Pizza pizza = dto.valueOf();
        pizzaRepository.persist(pizza);
        return pizza;
    }

    @Transactional
    @Override
    public Bebida createBebida(BebidaDTO dto) {
        Bebida bebida = dto.valueOf();
        bebidaRepository.persist(bebida);
        return bebida;
    }

    @Transactional
    @Override
    public Pizza updatePizza(PizzaDTO dto, Long id) {
        Pizza pizza = pizzaRepository.findById(id);
        if(dto.descricao() != null) pizza.setDescricao(dto.descricao());
        if(dto.ingredientes() != null) pizza.setIngredientes(dto.ingredientes());
        if(dto.kCal() != null) pizza.setkCal(dto.kCal());
        if(dto.nome() != null) pizza.setNome(dto.nome());
        if(dto.tamanhoPizza() != null) pizza.setTamanhoPizza(dto.tamanhoPizza());
        if(dto.preco() != null) pizza.setPreco(dto.preco());
        if(dto.tempoDePreparo() != null) pizza.setTempoDePreparo(dto.tempoDePreparo());
        pizzaRepository.persistAndFlush(pizza);
        return pizza;
    }

    @Transactional
    @Override
    public Bebida updateBebida(BebidaDTO dto, Long id) {
        Bebida bebida = bebidaRepository.findById(id);
        if(dto.descricao() != null) bebida.setDescricao(dto.descricao());
        if(dto.kCal() != null) bebida.setkCal(dto.kCal());
        if(dto.nome() != null) bebida.setNome(dto.nome());
        if(dto.preco() != null) bebida.setPreco(dto.preco());
        if(dto.ml() != null) bebida.setMl(dto.ml());
        bebidaRepository.persistAndFlush(bebida);
        return bebida;
    }

    @Override
    @Transactional
    public Item updateNomeImagem(Long id, String nomeImagem) {
        Item item;
        return null;
    }

    @Transactional
    @Override
    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteBebida(Long id) {
        bebidaRepository.deleteById(id);
    }

    @Override
    public Pizza findPizza(Long id) {
        return pizzaRepository.findById(id);
    }

    @Override
    public Bebida findBebida(Long id) {
        return bebidaRepository.findById(id);
    }
}
