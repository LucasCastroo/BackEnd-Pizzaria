package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.PedidoDTO;
import br.com.unitins.a1.dto.StatusPedidoDTO;
import br.com.unitins.a1.model.Cliente;
import br.com.unitins.a1.model.NivelAcesso;
import br.com.unitins.a1.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claim;

@Path("/pedido")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {
    @Inject
    @Claim("sub")
    Long idUsuario;

    @Inject
    PedidoService service;

    @POST
    @Path("/")
    @RolesAllowed(Cliente.ROLE)
    public Response create(PedidoDTO dto){
        return Response.status(Response.Status.CREATED).entity(service.create(dto, idUsuario)).build();
    }

    @PUT
    @Path("/")
    @RolesAllowed({Cliente.ROLE, NivelAcesso.Role.SUPERVISOR})
    public Response update(PedidoDTO dto){
        return Response.status(Response.Status.ACCEPTED).entity(service.update(dto, idUsuario)).build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({NivelAcesso.Role.SUPERVISOR, NivelAcesso.Role.ATENDENTE, NivelAcesso.Role.ADMIN})
    public Response updateStatus(StatusPedidoDTO dto, @PathParam("id") Long id){
        return Response.status(Response.Status.ACCEPTED).entity(service.updateStatus(dto, id)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({NivelAcesso.Role.ADMIN})
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({NivelAcesso.Role.GERENTE, NivelAcesso.Role.ADMIN})
    public Response findById(@PathParam("id") Long id){
        return Response.ok().entity(service.findById(id)).build();
    }

    @GET
    @RolesAllowed(Cliente.ROLE)
    public Response findByClienteId(){
        return Response.ok().entity(service.findByClienteId(idUsuario)).build();
    }

}
