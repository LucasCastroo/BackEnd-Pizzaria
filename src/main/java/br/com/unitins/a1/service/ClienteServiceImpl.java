package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.ClienteDTO;
import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.model.Cliente;
import br.com.unitins.a1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    ClienteRepository repository;

    @Override
    @Transactional
    public ClienteResponseDTO insert(ClienteDTO dto) {
        Cliente novoCliente = new Cliente();

        novoCliente.setNome(dto.getNome());
        novoCliente.setCpf(dto.getCpf());
        novoCliente.setEmail(dto.getEmail());
        novoCliente.setSenha(dto.getSenha());
        novoCliente.setNascimento(dto.getNascimento());
        novoCliente.setTelefone(dto.getTelefone());
        novoCliente.setEnderecos(dto.getEnderecos());

        repository.persist(novoCliente);

        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {
        Cliente Cliente = repository.findById(id);
        if (Cliente != null) {
            Cliente.setNome(dto.getNome());
            Cliente.setCpf(dto.getCpf());
            Cliente.setEmail(dto.getEmail());
            Cliente.setSenha(dto.getSenha());
            Cliente.setNascimento(dto.getNascimento());
            Cliente.setTelefone(dto.getTelefone());
            Cliente.setEnderecos(dto.getEnderecos());
        } else {
            throw new NotFoundException();
        }

        return ClienteResponseDTO.valueOf(Cliente);
    }

    @Override
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        return ClienteResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(n -> ClienteResponseDTO.valueOf(n)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(c -> ClienteResponseDTO.valueOf(c)).toList();
    }
}
