package br.unitins.tp1.pizzaria.repository;

import br.unitins.tp1.pizzaria.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public List<Cliente> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }
    public Cliente findByEmailSenha(String email, String senha) {
        try{
            return find("email = ?1 AND senha = ?2", email, senha).singleResult();
        }catch (NoResultException e){
            Log.error(e);
            return null;
        }
    }
}
