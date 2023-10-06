package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.EnderecoDTO;
import br.com.unitins.a1.dto.EnderecoResponseDTO;
import br.com.unitins.a1.model.Cliente;
import br.com.unitins.a1.model.Endereco;
import br.com.unitins.a1.repository.ClienteRepository;
import br.com.unitins.a1.repository.EnderecoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

public class EnderecoServiceImpl implements EnderecoService{

    @Inject
    ClienteRepository repositoryCliete;
    @Inject
    EnderecoRepository repositoryEndereco;

    @Transactional
    @Override
    public EnderecoResponseDTO insert(EnderecoDTO dto, Long idCliente) {
        Cliente cliente = repositoryCliete.findById(idCliente);
        Endereco novoEndereco = new Endereco();

        novoEndereco.setLogradouro(dto.getLogradouro());
        novoEndereco.setBairro(dto.getBairro());
        novoEndereco.setCidade(dto.getCidade());
        novoEndereco.setCep(dto.getCep());

        cliente.getEnderecos().add(novoEndereco);
        repositoryCliete.persist(cliente);
        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(EnderecoDTO dto, Long idEndereco) {
        Endereco endereco = repositoryEndereco.findById(idEndereco);
        if (endereco != null) {
            endereco.setLogradouro(dto.getLogradouro());
            endereco.setBairro(dto.getBairro());
            endereco.setCidade(dto.getCidade());
            endereco.setCep(dto.getCep());
        } else {
            throw new NotFoundException();
        }
        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    @Transactional
    public void delete(Long idEndereco) {
        if (!repositoryEndereco.deleteById(idEndereco)) {
            throw new NotFoundException();
        }
    }

    @Override
    public EnderecoResponseDTO findById(Long idEndereco) {
        return EnderecoResponseDTO.valueOf(repositoryEndereco.findById(idEndereco));
    }

    @Override
    public List<EnderecoResponseDTO> findByLogradouro(String logradouro) {
        return repositoryEndereco.findByLogradouro(logradouro).stream().map(l -> EnderecoResponseDTO.valueOf(l)).toList();
    }
}
