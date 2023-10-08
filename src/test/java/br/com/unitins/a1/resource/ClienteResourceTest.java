package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.ClienteDTO;
import br.com.unitins.a1.dto.EnderecoDTO;
import br.com.unitins.a1.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ClienteResourceTest {
    @Inject
    ClienteService service;


    @Test
    void insert() {
        ClienteDTO clienteDTO = new ClienteDTO(
                "Antônio Nunes",
                "768.957.726-18",
                "antonio@nunes.br",
                "12345688",
                "4002-8922",
                "03-04-1977",
                List.of(new EnderecoDTO("Rua 10 de abril Quadra 21 Casa 22", "Centro", "Palmas", "77064-000"))
                );
        given()
                .contentType(ContentType.JSON)
                .body(clienteDTO)
                .when().post("/clientes/")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", equalTo("Antônio Nunes")
                );
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByNome() {
    }
}