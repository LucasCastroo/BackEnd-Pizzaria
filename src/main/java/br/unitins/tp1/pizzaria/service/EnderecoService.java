package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.EnderecoDTO;
import br.unitins.tp1.pizzaria.dto.EnderecoResponseDTO;

import java.util.List;

public interface EnderecoService {
    public EnderecoResponseDTO insert(EnderecoDTO dto, Long idCliente);
    public EnderecoResponseDTO update(EnderecoDTO dto, Long idEndereco);
    public void delete(Long idEndereco);
    public EnderecoResponseDTO findById(Long idEndereco);
    public List<EnderecoResponseDTO> findByLogradouro(String logradouro);
}
