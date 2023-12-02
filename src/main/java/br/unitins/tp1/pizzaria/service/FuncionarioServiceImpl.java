package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.*;
import br.unitins.tp1.pizzaria.model.Funcionario;
import br.unitins.tp1.pizzaria.repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService, UsuarioService<FuncionarioResponseDTO> {

    @Inject
    FuncionarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public FuncionarioResponseDTO insert(FuncionarioDTO dto) {
        Funcionario novoFuncionario = new Funcionario();

        novoFuncionario.setNome(dto.getNome());
        novoFuncionario.setCpf(dto.getCpf());
        novoFuncionario.setEmail(dto.getEmail());
        novoFuncionario.setSenha(hashService.getHash(dto.getSenha()));
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
            funcionario.setSenha(hashService.getHash(dto.getSenha()));
            funcionario.setNascimento(dto.getNascimento());
            funcionario.setTipoAcesso(dto.getTipoAcesso());
            repository.persistAndFlush(funcionario);
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
        return repository.findByNome(nome).stream().map(FuncionarioResponseDTO::valueOf).toList();
    }

    @Override
    public FuncionarioResponseDTO findByEmailSenha(String email, String senha) {
        return FuncionarioResponseDTO.valueOf(repository.findByEmailSenha(email, senha));
    }

    @Override
    @Transactional
    public Boolean alterarSenha(AlterarSenhaDTO dto, Long id) {
        Funcionario funcionario = repository.findById(id);
        if(funcionario != null){
            if(hashService.getHash(dto.antigaSenha()).equals(funcionario.getSenha())){
                funcionario.setSenha(hashService.getHash(dto.novaSenha()));
                repository.persistAndFlush(funcionario);
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO alterarNome(NomeDTO nome, Long id) {
        Funcionario funcionario = repository.findById(id);
        funcionario.setNome(nome.nome());
        repository.persistAndFlush(funcionario);
        return FuncionarioResponseDTO.valueOf(funcionario);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO alterarCpf(CPFDTO cpf, Long id) {
        Funcionario funcionario = repository.findById(id);
        funcionario.setCpf(cpf.cpf());
        repository.persistAndFlush(funcionario);
        return FuncionarioResponseDTO.valueOf(funcionario);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO alterarEmail(EmailDTO email, Long id) {
        Funcionario funcionario = repository.findById(id);
        funcionario.setEmail(email.email());
        repository.persistAndFlush(funcionario);
        return FuncionarioResponseDTO.valueOf(funcionario);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO alterarNascimento(NascimentoDTO nascimento, Long id) {
        Funcionario funcionario = repository.findById(id);
        funcionario.setNascimento(nascimento.nascimento());
        repository.persistAndFlush(funcionario);
        return FuncionarioResponseDTO.valueOf(funcionario);
    }
}
