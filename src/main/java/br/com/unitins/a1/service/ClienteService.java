package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.ClienteDTO;
import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.dto.TelefoneDTO;

import java.util.List;

public interface ClienteService {
    public ClienteResponseDTO insert(ClienteDTO dto);
    public ClienteResponseDTO update(ClienteDTO dto, Long id);
    public void delete(Long id);
    public ClienteResponseDTO findById(Long id);
    public List<ClienteResponseDTO> findByNome(String nome);
    public List<ClienteResponseDTO> findByAll();
    public ClienteResponseDTO findByEmailSenha(String email, String senha);

    public ClienteResponseDTO alterarTelefone(TelefoneDTO telefone, Long id);

}
