package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.CupomDTO;
import br.unitins.tp1.pizzaria.dto.CupomResponseDTO;
import br.unitins.tp1.pizzaria.service.CupomService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
class CupomResourceTest {
    @Inject
    CupomService service;

    @Test
    void create() {
        CupomDTO dto = new CupomDTO(
                "CUPOMDETESTE",
                0.1
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/cupom")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "codigo", is("CUPOMDETESTE"),
                        "desconto", is(0.1f)
                );
    }

    @Test
    void update() {
        CupomDTO dto = new CupomDTO(
                "CUPOMDETESTE",
                0.1
        );
        CupomDTO dtoUpdate = new CupomDTO(
                "CUPOMDETESTE2",
                0.5
        );
        CupomResponseDTO cupom = service.create(dto);

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/cupom/" + cupom.id())
                .then()
                .statusCode(204);

        CupomResponseDTO cup = service.findById(cupom.id());
        assertThat(cup.codigo(), is("CUPOMDETESTE2"));
        assertThat(cup.desconto(), is(0.5));
    }

    @Test
    void delete() {
        CupomDTO dto = new CupomDTO(
                "CUPOMDETESTE",
                0.1
        );
        CupomResponseDTO cupom = service.create(dto);
        given()
                .when().delete("/cupom/" + cupom.id())
                .then()
                .statusCode(204);

        assertNull(service.findById(cupom.id()));
    }

    @Test
    void findById() {
        CupomDTO dto = new CupomDTO(
                "CUPOMDETESTE",
                0.1
        );
        CupomResponseDTO cupom = service.create(dto);
        given()
                .when().get("/cupom/" + cupom.id())
                .then()
                .statusCode(200)
                .body(
                        "id", is(cupom.id().intValue())
                );
    }

    @Test
    void findByCodigo() {
        CupomDTO dto = new CupomDTO(
                "CUPOMDETESTE",
                0.1
        );
        CupomResponseDTO cupom = service.create(dto);

        given()
                .when().get("/cupom/busca/" + cupom.codigo())
                .then()
                .statusCode(200);

    }
}