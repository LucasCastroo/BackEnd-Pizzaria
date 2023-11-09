package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.dto.FuncionarioResponseDTO;
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
                .groups(Set.of("USER"))
                .expiresAt(getExpiration())
                .sign();
    }

    @Override
    public String generateJwt(FuncionarioResponseDTO usuario) {
        return Jwt.issuer("pizzaria-jwt")
                .subject(String.valueOf(usuario.id()))
                .upn(usuario.email())
                .groups(Set.of("FUNCIONARIO", usuario.tipoAcesso().name()))
                .expiresAt(getExpiration())
                .sign();
    }

    private Instant getExpiration(){
        return Instant.now().plus(TOKEN_DURATION);
    }
}
