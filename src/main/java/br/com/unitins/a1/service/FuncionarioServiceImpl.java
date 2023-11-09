package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.FuncionarioDTO;
import br.com.unitins.a1.dto.FuncionarioResponseDTO;
import br.com.unitins.a1.model.Funcionario;
import br.com.unitins.a1.repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    FuncionarioRepository repository;

    @Override
    @Transactional
    public FuncionarioResponseDTO insert(FuncionarioDTO dto) {
        Funcionario novoFuncionario = new Funcionario();

        novoFuncionario.setNome(dto.getNome());
        novoFuncionario.setCpf(dto.getCpf());
        novoFuncionario.setEmail(dto.getEmail());
        novoFuncionario.setSenha(dto.getSenha());
        novoFuncionario.setNascimento(dto.getNascimento());
        novoFuncionario.setTipoAcesso(dto.getTipoAcesso());

        repository.persist(novoFuncionario);

        return FuncionarioResponseDTO.valueOf(novoFuncionario);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO update(FuncionarioDTO dto, Long id) {
        Funcionario funcionario = repository.findById(id);
        if (funcionario != null) {
            funcionario.setNome(dto.getNome());
            funcionario.setCpf(dto.getCpf());
            funcionario.setEmail(dto.getEmail());
            funcionario.setSenha(dto.getSenha());
            funcionario.setNascimento(dto.getNascimento());
            funcionario.setTipoAcesso(dto.getTipoAcesso());
        } else {
            throw new NotFoundException();
        }

        return FuncionarioResponseDTO.valueOf(funcionario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    @Override
    public FuncionarioResponseDTO findById(Long id) {
        return FuncionarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<FuncionarioResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(n -> FuncionarioResponseDTO.valueOf(n)).toList();
    }

    @Override
    public FuncionarioResponseDTO findByEmailSenha(String email, String senha) {
        return FuncionarioResponseDTO.valueOf(repository.findByEmailSenha(email, senha));
    }
}
