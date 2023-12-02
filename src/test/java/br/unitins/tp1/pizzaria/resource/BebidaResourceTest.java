package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.BebidaDTO;
import br.unitins.tp1.pizzaria.dto.BebidaResponseDTO;
import br.unitins.tp1.pizzaria.model.Funcionario;
import br.unitins.tp1.pizzaria.model.NivelAcesso;
import br.unitins.tp1.pizzaria.service.BebidaServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.ClaimType;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
@TestSecurity(user = "funcionario1@email.com", roles = {Funcionario.ROLE, NivelAcesso.Role.ADMIN})
@JwtSecurity(
        claims = {
                @Claim(key = "sub", value = "1", type = ClaimType.LONG)
        }
)
public class BebidaResourceTest {

    @Inject
    BebidaServiceImpl bebidaService;


    @Test
    void createBebida() {
        BebidaDTO dto = new BebidaDTO(
                "Coca-Cola",
                "Bebida Gelada",
                4.0,
                100,
                350
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/bebida/create")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Coca-Cola"),
                        "descricao", is("Bebida Gelada"),
                        "preco", is(4.0f),
                        "kCal", is(100),
                        "ml", is(350)
                );
    }

    @Test
    void updateBebida() {
        BebidaDTO dto = new BebidaDTO(
                "Coca-Cola",
                "Bebida Gelada",
                4.0,
                100,
                350
        );

        BebidaResponseDTO bebidaTest = bebidaService.create(dto);
        Long id = bebidaTest.getId();

        BebidaDTO dtoUpdate = new BebidaDTO(
                "Coca-Cola",
                "Bebida Gelada",
                6.0,
                100,
                350
        );

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/bebida/update/" + id)
                .then()
                .statusCode(202);

        BebidaResponseDTO beb = bebidaService.findById(id);
        assertThat(beb.getNome(), is("Coca-Cola"));
        assertThat(beb.getDescricao(), is("Bebida Gelada"));
        assertThat(beb.getPreco(), is(6.0));
        assertThat(beb.getkCal(), is(100));
        assertThat(beb.getMl(), is(350));
    }

    @Test
    void findBebida() {
        BebidaDTO dto = new BebidaDTO(
                "Coca-Cola",
                "Bebida Gelada",
                4.0,
                100,
                350
        );

        BebidaResponseDTO bebidaTest = bebidaService.create(dto);

        given()
                .when()
                .get("/bebida/" + bebidaTest.getId())
                .then()
                .statusCode(200);
    }
}
