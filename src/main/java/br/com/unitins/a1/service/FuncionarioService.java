package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.dto.FuncionarioDTO;
import br.com.unitins.a1.dto.FuncionarioResponseDTO;

import java.util.List;

public interface FuncionarioService {
    public FuncionarioResponseDTO insert(FuncionarioDTO dto);
    public FuncionarioResponseDTO update(FuncionarioDTO dto, Long id);
    public void delete(Long id);
    public FuncionarioResponseDTO findById(Long id);
    public List<FuncionarioResponseDTO> findByNome(String nome);

    public FuncionarioResponseDTO findByEmailSenha(String email, String senha);

}
