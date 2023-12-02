package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.dto.FuncionarioResponseDTO;
import br.com.unitins.a1.dto.LoginDTO;
import br.com.unitins.a1.service.ClienteService;
import br.com.unitins.a1.service.FuncionarioService;
import br.com.unitins.a1.service.HashService;
import br.com.unitins.a1.service.JwtService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    @Inject
    ClienteService clienteService;
    @Inject
    FuncionarioService funcionarioService;
    @Inject
    HashService hashService;
    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Path("/cliente")
    public Response loginCliente(@Valid LoginDTO dto){
        try {
            String hashed = hashService.getHash(dto.senha());
            ClienteResponseDTO cliente = clienteService.findByEmailSenha(dto.email(), hashed);
            if (cliente == null) {
                LOG.errorf("Login de %s mal sucedido!", dto.email());
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas").build();
            }
            String token = jwtService.generateJwt(cliente);
            LOG.infof("Login de %s feito com sucesso!", dto.email());
            return Response.ok().header("Authorization", token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
        }
    }

    @POST
    @Path("/funcionario")
    public Response loginFuncionario(@Valid LoginDTO dto){
        try {
            String hashed = hashService.getHash(dto.senha());
            FuncionarioResponseDTO funcionario = funcionarioService.findByEmailSenha(dto.email(), hashed);
                if (funcionario == null) {
                    LOG.errorf("Login de %s mal sucedido!", dto.email());
                    return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas").build();
            }
            String token = jwtService.generateJwt(funcionario);
            LOG.infof("Login de %s feito com sucesso!", dto.email());
            return Response.ok().header("Authorization", token).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
        }
    }
}
