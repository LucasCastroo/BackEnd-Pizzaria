package br.com.unitins.a1.repository;

import br.com.unitins.a1.model.Pizza;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PizzaRepository implements PanacheRepository<Pizza> {
}
