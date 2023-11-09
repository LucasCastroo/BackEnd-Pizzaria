package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.AlterarSenhaDTO;
import br.com.unitins.a1.dto.ClienteDTO;
import br.com.unitins.a1.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    public ClienteResponseDTO insert(ClienteDTO dto);
    public ClienteResponseDTO update(ClienteDTO dto, Long id);
    public void delete(Long id);
    public ClienteResponseDTO findById(Long id);
    public List<ClienteResponseDTO> findByNome(String nome);
    public List<ClienteResponseDTO> findByAll();
    public ClienteResponseDTO findByEmailSenha(String email, String senha);
    public Boolean alterarSenha(AlterarSenhaDTO dto, Long id);
}
