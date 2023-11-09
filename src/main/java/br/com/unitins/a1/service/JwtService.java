package br.com.unitins.a1.service;
;
import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.dto.FuncionarioResponseDTO;

public interface JwtService {
    public String generateJwt(ClienteResponseDTO usuario);
    public String generateJwt(FuncionarioResponseDTO usuario);
}
