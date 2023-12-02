package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.ClienteDTO;
import br.unitins.tp1.pizzaria.dto.ClienteResponseDTO;
import br.unitins.tp1.pizzaria.dto.EnderecoDTO;
import br.unitins.tp1.pizzaria.dto.EnderecoResponseDTO;
import br.unitins.tp1.pizzaria.service.ClienteService;
import br.unitins.tp1.pizzaria.service.EnderecoService;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
class EnderecoResourceTest {

    @Inject
    EnderecoService enderecoService;

    @Inject
    ClienteService clienteService;

    @Test
    void insert() {
        EnderecoDTO dto = new EnderecoDTO(
                "Rua 01, Qd 01, Lote 01",
                "Palmas Brasil",
                "Palmas",
                "11111-111"
        );
        ClienteDTO clienteDTO = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(11) 11111-1111",
                LocalDate.of(1994,1,1),
                null
        );

        ClienteResponseDTO cliente = clienteService.insert(clienteDTO);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/enderecos/criar/" + cliente.id())
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

        ClienteDTO clienteDTO = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(11) 11111-1111",
                LocalDate.of(1994,1,1),
                null
        );

        ClienteResponseDTO cliente = clienteService.insert(clienteDTO);

        EnderecoResponseDTO enderecoTest = enderecoService.insert(dto, cliente.id());

        EnderecoDTO dtoUpdate = new EnderecoDTO(
                "Rua 05, Qd 01, Lote 10",
                "303 sul",
                "Palmas",
                "22222-222"
        );

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/enderecos/"+ enderecoTest.id())
                .then()
                .statusCode(204);

        EnderecoResponseDTO end = enderecoService.findById(enderecoTest.id());
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
        ClienteDTO clienteDTO = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(11) 11111-1111",
                LocalDate.of(1994,1,1),
                null
        );

        ClienteResponseDTO cliente = clienteService.insert(clienteDTO);

        EnderecoResponseDTO endereco = enderecoService.insert(dto, cliente.id());
        Log.info(endereco);
        given()
                .when().delete("/enderecos/" + endereco.id())
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

        ClienteDTO clienteDTO = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(11) 11111-1111",
                LocalDate.of(1994,1,1),
                null
        );

        ClienteResponseDTO cliente = clienteService.insert(clienteDTO);

        EnderecoResponseDTO endereco = enderecoService.insert(dto, cliente.id());

        given()
                .when().get("/enderecos/search/id/" + endereco.id())
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

        EnderecoResponseDTO endereco = enderecoService.insert(dto, 1L);

        given()
                .when().get("/enderecos/search/logradouro/Rua 01, Qd 01, Lote 01")
                .then()
                .statusCode(200)
                .body(
                        "id", hasItem(endereco.id().intValue())
                );
    }
}