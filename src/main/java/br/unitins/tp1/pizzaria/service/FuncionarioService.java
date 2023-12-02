package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.FuncionarioDTO;
import br.unitins.tp1.pizzaria.dto.FuncionarioResponseDTO;

import java.util.List;

public interface FuncionarioService {
    public FuncionarioResponseDTO insert(FuncionarioDTO dto);
    public FuncionarioResponseDTO update(FuncionarioDTO dto, Long id);
    public void delete(Long id);
    public FuncionarioResponseDTO findById(Long id);
    public List<FuncionarioResponseDTO> findByNome(String nome);
    public FuncionarioResponseDTO findByEmailSenha(String email, String senha);

}
