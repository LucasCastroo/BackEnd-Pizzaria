package br.unitins.tp1.pizzaria.service;

import br.unitins.tp1.pizzaria.dto.ClienteResponseDTO;
import br.unitins.tp1.pizzaria.dto.FuncionarioResponseDTO;
import br.unitins.tp1.pizzaria.model.Cliente;
import br.unitins.tp1.pizzaria.model.Funcionario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class JwtServiceImpl implements JwtService{

    private static final Duration TOKEN_DURATION = Duration.ofHours(24);

    @Override
    public String generateJwt(ClienteResponseDTO usuario) {
        return Jwt.issuer("pizzaria-jwt")
                .subject(String.valueOf(usuario.id()))
                .upn(usuario.email())
                .groups(Set.of(Cliente.ROLE))
                .expiresAt(getExpiration())
                .sign();
    }

    @Override
    public String generateJwt(FuncionarioResponseDTO usuario) {
        return Jwt.issuer("pizzaria-jwt")
                .subject(String.valueOf(usuario.id()))
                .upn(usuario.email())
                .groups(Set.of(Funcionario.ROLE, usuario.tipoAcesso().name()))
                .expiresAt(getExpiration())
                .sign();
    }

    private Instant getExpiration(){
        return Instant.now().plus(TOKEN_DURATION);
    }
}
