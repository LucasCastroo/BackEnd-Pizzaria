package br.unitins.tp1.pizzaria.repository;

import br.unitins.tp1.pizzaria.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    public List<Endereco> findByLogradouro(String logradouro) {
        return find("UPPER(logradouro) LIKE UPPER(?1) ", "%"+logradouro+"%").list();
    }

    public boolean delete(Long id){
        try {
            getEntityManager().createNativeQuery("delete from cliente_endereco where id_endereco = " + id).executeUpdate();

            return deleteById(id);
        } catch (Exception e) {
            return false;
        }
    }
}
