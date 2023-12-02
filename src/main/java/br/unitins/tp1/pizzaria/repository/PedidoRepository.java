package br.unitins.tp1.pizzaria.repository;

import br.unitins.tp1.pizzaria.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public List<Pedido> findByClienteId(Long idCliente) {
        return find("SELECT p from Pedido p JOIN p.cliente c WHERE c.id=?1", idCliente).list();
    }
}
