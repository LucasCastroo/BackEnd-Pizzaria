package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.EnderecoDTO;
import br.unitins.tp1.pizzaria.dto.EnderecoResponseDTO;
import br.unitins.tp1.pizzaria.model.Cliente;
import br.unitins.tp1.pizzaria.model.Endereco;
import br.unitins.tp1.pizzaria.repository.ClienteRepository;
import br.unitins.tp1.pizzaria.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService{

    @Inject
    ClienteRepository repositoryCliente;
    @Inject
    EnderecoRepository repositoryEndereco;

    @Transactional
    @Override
    public EnderecoResponseDTO insert(EnderecoDTO dto, Long idCliente) {
        Cliente cliente = repositoryCliente.findById(idCliente);
        Endereco novoEndereco = new Endereco(dto.getLogradouro(), dto.getBairro(), dto.getCidade(), dto.getCep());

        cliente.getEnderecos().add(novoEndereco);
        repositoryCliente.persistAndFlush(cliente);
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
            repositoryEndereco.persistAndFlush(endereco);
        } else {
            throw new NotFoundException();
        }
        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    @Transactional
    public void delete(Long idEndereco) {
        if (!repositoryEndereco.delete(idEndereco)) {
            throw new NotFoundException();
        }
    }

    @Override
    public EnderecoResponseDTO findById(Long idEndereco) {
        return EnderecoResponseDTO.valueOf(repositoryEndereco.findById(idEndereco));
    }

    @Override
    public List<EnderecoResponseDTO> findByLogradouro(String logradouro) {
        return repositoryEndereco.findByLogradouro(logradouro).stream().map(EnderecoResponseDTO::valueOf).toList();
    }
}
