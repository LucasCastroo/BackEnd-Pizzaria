package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.CupomDTO;
import br.unitins.tp1.pizzaria.dto.CupomResponseDTO;
import br.unitins.tp1.pizzaria.model.NivelAcesso;
import br.unitins.tp1.pizzaria.service.CupomService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/cupom")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CupomResource {
    @Inject
    CupomService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @RolesAllowed({NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response create(@Valid CupomDTO dto){
        LOG.infof("Novo cupom %s adicionado!", dto.codigo());
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response update(CupomDTO dto, @PathParam("id") Long id){
        service.update(dto, id);
        LOG.infof("Cupom atualizado!");
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        LOG.infof("Cupom id=%d deletado!", id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response findById(@PathParam("id") Long id){
        LOG.infof("Busca de cupom por %d!", id);
        return Response.ok().entity(service.findById(id)).build();
    }

    @GET
    @Path("/busca/{codigo}")
    @RolesAllowed({NivelAcesso.Role.GERENTE,NivelAcesso.Role.ADMIN})
    public Response findByCodigo(@PathParam("codigo") String codigo){
        CupomResponseDTO cupom = service.findByCodigo(codigo);
        if(cupom != null) {
            LOG.infof("Busca de cupom por %s!", codigo);
            return Response.ok().entity(cupom).build();
        } else
            return Response.status(404).build();
    }
}
