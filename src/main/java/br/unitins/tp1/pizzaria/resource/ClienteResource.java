package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.ClienteDTO;
import br.unitins.tp1.pizzaria.dto.ClienteResponseDTO;
import br.unitins.tp1.pizzaria.model.Cliente;
import br.unitins.tp1.pizzaria.model.NivelAcesso;
import br.unitins.tp1.pizzaria.service.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
    @Inject
    ClienteService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    public Response insert(@Valid ClienteDTO dto) {
        ClienteResponseDTO retorno = service.insert(dto);
        LOG.infof("Novo cliente id=%d cadastrado!", retorno.id());
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({Cliente.ROLE})
    public Response update(ClienteDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        LOG.infof("Informações do cliente id=%d atualizadas!", id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({Cliente.ROLE, NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        LOG.infof("Cliente id=%d deletado!", id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({NivelAcesso.Role.SUPERVISOR, NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response findAll() {
        LOG.infof("Busca de todos os clientes");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    @RolesAllowed({NivelAcesso.Role.SUPERVISOR, NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Busca de um cliente id=%d", id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({NivelAcesso.Role.SUPERVISOR, NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("Busca de um cliente por %s", nome);
        return Response.ok(service.findByNome(nome)).build();
    }
}
