package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.EnderecoDTO;
import br.com.unitins.a1.dto.EnderecoResponseDTO;

import java.util.List;

public interface EnderecoService {
    public EnderecoResponseDTO insert(EnderecoDTO dto, Long idCliente);
    public EnderecoResponseDTO update(EnderecoDTO dto, Long idEndereco);
    public void delete(Long idEndereco);
    public EnderecoResponseDTO findById(Long idEndereco);
    public List<EnderecoResponseDTO> findByLogradouro(String logradouro);
}
