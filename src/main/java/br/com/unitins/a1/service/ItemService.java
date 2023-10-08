package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.BebidaDTO;
import br.com.unitins.a1.dto.PizzaDTO;
import br.com.unitins.a1.model.Bebida;
import br.com.unitins.a1.model.Pizza;

public interface ItemService {
    Pizza createPizza(PizzaDTO dto);
    Bebida createBebida(BebidaDTO dto);
    Pizza updatePizza(PizzaDTO dto, Long id);
    Bebida updateBebida(BebidaDTO dto, Long id);
    void deletePizza(Long id);
    void deleteBebida(Long id);
    Pizza findPizza(Long id);
    Bebida findBebida(Long id);
}
