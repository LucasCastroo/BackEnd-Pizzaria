package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.AlterarSenhaDTO;
import br.com.unitins.a1.dto.ClienteDTO;
import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.model.Cliente;
import br.com.unitins.a1.model.Endereco;
import br.com.unitins.a1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    ClienteRepository repository;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public ClienteResponseDTO insert(ClienteDTO dto) {
        Cliente novoCliente = new Cliente();

        novoCliente.setNome(dto.getNome());
        novoCliente.setCpf(dto.getCpf());
        novoCliente.setEmail(dto.getEmail());
        novoCliente.setSenha(hashService.getHash(dto.getSenha()));
        novoCliente.setNascimento(dto.getNascimento());
        novoCliente.setTelefone(dto.getTelefone());
        if(dto.getEnderecos() != null) novoCliente.setEnderecos(dto.getEnderecos().stream().map(e -> new Endereco(e.getLogradouro(), e.getBairro(), e.getCidade(), e.getCep())).toList());
        else novoCliente.setEnderecos(Collections.emptyList());

        repository.persist(novoCliente);

        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {
        Cliente cliente = repository.findById(id);
        if (cliente != null) {
            cliente.setNome(dto.getNome());
            cliente.setCpf(dto.getCpf());
            cliente.setEmail(dto.getEmail());
            cliente.setSenha(hashService.getHash(dto.getSenha()));
            cliente.setNascimento(dto.getNascimento());
            cliente.setTelefone(dto.getTelefone());
            cliente.getEnderecos().clear();
            cliente.getEnderecos().addAll(dto.getEnderecos().stream().map(e -> new Endereco(e.getLogradouro(), e.getBairro(), e.getCidade(), e.getCep())).toList());
        } else {
            throw new NotFoundException();
        }

        return ClienteResponseDTO.valueOf(cliente);
    }

    @Override
    @Transactional
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
        return repository.findByNome(nome).stream().map(ClienteResponseDTO::valueOf).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(ClienteResponseDTO::valueOf).toList();
    }

    @Override
    public ClienteResponseDTO findByEmailSenha(String email, String senha) {
        return ClienteResponseDTO.valueOf(repository.findByEmailSenha(email, senha));
    }

    @Override
    @Transactional
    public Boolean alterarSenha(AlterarSenhaDTO dto, Long id) {
        Cliente cliente = repository.findById(id);
        if(cliente != null){
            if(hashService.getHash(dto.antigaSenha()).equals(cliente.getSenha())){
                cliente.setSenha(hashService.getHash(dto.novaSenha()));
                repository.persist(cliente);
                return true;
            }
        }
        return false;
    }
}
