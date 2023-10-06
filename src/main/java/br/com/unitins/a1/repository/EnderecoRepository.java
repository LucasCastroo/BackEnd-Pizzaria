package br.com.unitins.a1.repository;

import br.com.unitins.a1.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    public List<Endereco> findByLogradouro(String logradouro) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+logradouro+"%").list();
    }
}
