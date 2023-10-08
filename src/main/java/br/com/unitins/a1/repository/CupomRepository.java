package br.com.unitins.a1.repository;

import br.com.unitins.a1.model.Cupom;
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
