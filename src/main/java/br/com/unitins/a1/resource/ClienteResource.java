package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.ClienteDTO;
import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.service.ClienteService;
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
        LOG.info("Novo cliente cadastrado!");
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(ClienteDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        LOG.info("Informações de cliente atualizadas!");
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        LOG.info("Cliente deletado!");
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        LOG.info("Busca de todos os clientes!");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Busca de um cliente por ID!");
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Busca de um cliente por NOME!");
        return Response.ok(service.findByNome(nome)).build();
    }
}
