package br.com.unitins.a1.repository;

import br.com.unitins.a1.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public List<Pedido> findByClienteId(Long idCliente) {
        return find("SELECT p from Pedido p JOIN p.cliente c WHERE c.id=?1", idCliente).list();
    }
}
