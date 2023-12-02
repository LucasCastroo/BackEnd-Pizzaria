package br.unitins.tp1.pizzaria.repository;

import br.unitins.tp1.pizzaria.model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

import java.util.List;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
    public List<Funcionario> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }

    public Funcionario findByEmailSenha(String email, String senha) {
        try {
            return find("email = ?1 AND senha = ?2", email, senha).singleResult();
        }catch (NoResultException e){
            Log.error(e);
            return null;
        }
    }
}
