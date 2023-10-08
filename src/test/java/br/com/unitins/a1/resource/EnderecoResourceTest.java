package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.*;
import br.com.unitins.a1.model.Endereco;
import br.com.unitins.a1.model.NivelAcesso;
import br.com.unitins.a1.service.EnderecoService;
import br.com.unitins.a1.service.FuncionarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class EnderecoResourceTest {

    @Inject
    EnderecoService enderecoService;
    @Test
    void insert() {
        EnderecoDTO dto = new EnderecoDTO(
                "Rua 01, Qd 01, Lote 01",
                "Palmas Brasil",
                "Palmas",
                "11111-111"
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/enderecos/criar/2")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "logradouro", is("Rua 01, Qd 01, Lote 01"),
                        "bairro", is("Palmas Brasil"),
                        "cidade", is("Palmas"),
                        "cep", is("11111-111")
                );
    }

    @Test
    void update() {
        EnderecoDTO dto = new EnderecoDTO(
                "Rua 01, Qd 01, Lote 01",
                "Palmas Brasil",
                "Palmas",
                "11111-111"
        );

        EnderecoResponseDTO enderecoTest = enderecoService.insert(dto);
        Long id = enderecoTest.id();

        EnderecoDTO dtoUpdate = new EnderecoDTO(
                "Rua 05, Qd 01, Lote 10",
                "303 sul",
                "Palmas",
                "22222-222"
        );

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/enderecos/criar"+ id)
                .then()
                .statusCode(204);

        EnderecoResponseDTO end = enderecoService.findById(id);
        assertThat(end.logradouro(), is("Rua 05, Qd 01, Lote 10"));
        assertThat(end.bairro(), is("303 sul"));
        assertThat(end.cidade(), is("Palmas"));
        assertThat(end.cep(), is("22222-222"));
    }

    @Test
    void delete() {
        EnderecoDTO dto = new EnderecoDTO(
                "Rua 01, Qd 01, Lote 01",
                "Palmas Brasil",
                "Palmas",
                "11111-111"
        );

        EnderecoResponseDTO endereco = enderecoService.insert(dto);
        given()
                .when().delete("/endereco/" + endereco.id())
                .then()
                .statusCode(204);

        assertNull(enderecoService.findById(endereco.id()));
    }

    @Test
    void findById() {
        EnderecoDTO dto = new EnderecoDTO(
                "Rua 01, Qd 01, Lote 01",
                "Palmas Brasil",
                "Palmas",
                "11111-111"
        );

        EnderecoResponseDTO endereco = enderecoService.insert(dto);

        given()
                .when().get("/funcionarios/search/id/" + endereco.id())
                .then()
                .statusCode(200)
                .body(
                        "id", is(endereco.id().intValue())
                );
    }

    @Test
    void findByLogradouro() {
        EnderecoDTO dto = new EnderecoDTO(
                "Rua 01, Qd 01, Lote 01",
                "Palmas Brasil",
                "Palmas",
                "11111-111"
        );

        EnderecoResponseDTO endereco = enderecoService.insert(dto);

        given()
                .when().get("/endereco/search/logradouro/???")
                .then()
                .statusCode(200)
                .body(
                        "id", hasItem(endereco.id().intValue())
                );
    }
}