package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.ClienteResponseDTO;
import br.unitins.tp1.pizzaria.dto.FuncionarioResponseDTO;

;

public interface JwtService {
    public String generateJwt(ClienteResponseDTO usuario);
    public String generateJwt(FuncionarioResponseDTO usuario);
}
