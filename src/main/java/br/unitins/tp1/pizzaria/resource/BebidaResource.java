package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.BebidaDTO;
import br.unitins.tp1.pizzaria.service.BebidaServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@ApplicationScoped
@Path("/bebida")
@SecurityRequirement(name = "SecurityScheme") // sem isso o swagger não usa autorização
public class BebidaResource extends ItemResource<BebidaServiceImpl, BebidaDTO> {
}
