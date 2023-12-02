package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.ClienteDTO;
import br.unitins.tp1.pizzaria.dto.ClienteResponseDTO;
import br.unitins.tp1.pizzaria.dto.TelefoneDTO;

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
