package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.PedidoDTO;
import br.com.unitins.a1.dto.StatusPedidoDTO;
import br.com.unitins.a1.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedido")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {
    @Inject
    PedidoService service;

    @POST
    @Path("/criar/{id_cliente}")
    public Response create(PedidoDTO dto, @PathParam("id_cliente") Long idCliente){
        return Response.status(Response.Status.CREATED).entity(service.create(dto, idCliente)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(PedidoDTO dto, @PathParam("id") Long id){
        return Response.status(Response.Status.ACCEPTED).entity(service.update(dto, id)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response updateStatus(StatusPedidoDTO dto, @PathParam("id") Long id){
        return Response.status(Response.Status.ACCEPTED).entity(service.updateStatus(dto, id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok().entity(service.findById(id)).build();
    }

    @GET
    @Path("/busca/{id_cliente}")
    public Response findByClienteId(@PathParam("id_cliente") Long idCliente){
        return Response.ok().entity(service.findByClienteId(idCliente)).build();
    }

}
