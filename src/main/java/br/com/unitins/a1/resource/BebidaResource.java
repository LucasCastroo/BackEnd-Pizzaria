package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.BebidaDTO;
import br.com.unitins.a1.service.BebidaServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@ApplicationScoped
@Path("/bebida")
@SecurityRequirement(name = "SecurityScheme")
public class BebidaResource extends ItemResource<BebidaServiceImpl, BebidaDTO> {
}
