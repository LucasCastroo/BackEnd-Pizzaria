package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.*;
import br.unitins.tp1.pizzaria.model.Cliente;
import br.unitins.tp1.pizzaria.model.Funcionario;
import br.unitins.tp1.pizzaria.service.ClienteServiceImpl;
import br.unitins.tp1.pizzaria.service.FuncionarioServiceImpl;
import br.unitins.tp1.pizzaria.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Path("/minha-conta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({Cliente.ROLE, Funcionario.ROLE})
public class UsuarioLogadoResource {
    private static final Logger LOG = Logger.getLogger(AuthResource.class);
    @Inject
    @Claim("sub")
    Long idUsuario;
    @Inject
    JsonWebToken jwt;
    @Inject
    ClienteServiceImpl clienteService;
    @Inject
    FuncionarioServiceImpl funcionarioService;

    @GET
    public Response minhaConta() {
        return Response.ok(getUsuarioService().findById(idUsuario)).build();
    }

    @PATCH
    @Path("/alterar-senha")
    public Response alterarSenha(@Valid AlterarSenhaDTO dto) {
        try {
            getUsuarioService().alterarSenha(dto, idUsuario);
            LOG.info("Senha alterada");
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.error("Erro ao alterar a senha: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar a senha.").build();
        }
    }

    @PATCH
    @Path("/alterar-nome")
    public Response alterarNome(@Valid NomeDTO dto) {
        try {
            return Response.ok(getUsuarioService().alterarNome(dto, idUsuario)).build();
        } catch (Exception e) {
            LOG.error("Erro ao alterar o nome: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar nome.").build();
        }
    }

    @PATCH
    @Path("/alterar-email")
    public Response alterarEmail(@Valid EmailDTO dto) {
        try {
            return Response.ok(getUsuarioService().alterarEmail(dto, idUsuario)).build();
        } catch (Exception e) {
            LOG.error("Erro ao alterar o email: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar email.").build();
        }
    }

    @PATCH
    @Path("/alterar-cpf")
    public Response alterarCpf(@Valid CPFDTO dto) {
        try {
            return Response.ok(getUsuarioService().alterarCpf(dto, idUsuario)).build();
        } catch (Exception e) {
            LOG.error("Erro ao alterar o cpf: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar cpf.").build();
        }
    }

    @PATCH
    @Path("/alterar-nascimento")
    public Response alterarNascimento(@Valid NascimentoDTO dto) {
        try {
            return Response.ok(getUsuarioService().alterarNascimento(dto, idUsuario)).build();
        } catch (Exception e) {
            LOG.error("Erro ao alterar o nascimento: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar nascimento.").build();
        }
    }

    @PATCH
    @Path("/alterar-telefone")
    public Response alterarTelefone(@Valid TelefoneDTO dto) {
        try {
            return Response.ok(clienteService.alterarTelefone(dto, idUsuario)).build();
        } catch (Exception e) {
            LOG.error("Erro ao alterar o telefone: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao alterar telefone.").build();
        }
    }

    private UsuarioService getUsuarioService() {
        if (jwt.getGroups().contains(Funcionario.ROLE)) {
            return funcionarioService;
        } else if (jwt.getGroups().contains(Cliente.ROLE)) {
            return clienteService;
        }
        throw new RuntimeException();
    }
}
