package br.unitins.tp1.pizzaria.repository;

import br.unitins.tp1.pizzaria.model.Cupom;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CupomRepository implements PanacheRepository<Cupom> {
    public Cupom findByCodigo(String codigo) {
        try {
            return find("LOWER(codigo) = LOWER(?1)", codigo).firstResult();
        } catch (Exception e) {
            return null;
        }
    }
}
