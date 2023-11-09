package br.com.unitins.a1.repository;

import br.com.unitins.a1.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public List<Cliente> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }
    public Cliente findByEmailSenha(String email, String senha) {
        return find("email = ?1 AND senha = ?2", email, senha).singleResult();
    }
}
