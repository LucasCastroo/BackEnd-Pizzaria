package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.ClienteDTO;
import br.unitins.tp1.pizzaria.dto.ClienteResponseDTO;
import br.unitins.tp1.pizzaria.dto.EnderecoDTO;
import br.unitins.tp1.pizzaria.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class ClienteResourceTest {

    @Inject
    ClienteService clienteService;

    @Test
    void testFindAll() {
        given()
                .when().get("/clientes")
                .then()
                .statusCode(200);
    }

    @Test
    void testInsert() {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(new EnderecoDTO("Rua 01, Qd 02, Lote 01", "Bairro algumaCoisa", "Palmas",  "77777-777"));
        ClienteDTO dto = new ClienteDTO(
                "Janio Junior",
                "261.460.670-75",
                "janio@gmail.com",
                "12345678",
                "63999978459",
                LocalDate.of(1994,1,1),
                enderecos
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/clientes")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Janio Junior"),
                        "cpf", is("261.460.670-75"),
                        "email", is("janio@gmail.com"),
                        "telefone", is("63999978459")
                );
    }

    @Test
    void testUpdate() {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(new EnderecoDTO("Rua 01, Qd 02, Lote 01", "Bairro algumaCoisa", "Palmas", "77777-777"));
        ClienteDTO dto = new ClienteDTO(
                "Janio Junior",
                "261.460.670-75",
                "janio@gmail.com",
                "12345678",
                "63999978459",
                LocalDate.of(1994,1,1),
                enderecos
        );

        ClienteResponseDTO clienteTest = clienteService.insert(dto);
        Long id = clienteTest.id();

        ClienteDTO dtoUpdate = new ClienteDTO(
                "Janio Junior",
                "261.460.670-75",
                "janio@gmail.com",
                "12345678",
                "63999978459",
                LocalDate.of(1994,1,1),
                enderecos
        );

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/clientes/"+ id)
                .then()
                .statusCode(204);

        ClienteResponseDTO cli = clienteService.findById(id);
        assertThat(cli.nome(), is("Janio Junior"));
        assertThat(cli.cpf(), is("261.460.670-75"));
        assertThat(cli.email(), is("janio@gmail.com"));
        assertThat(cli.telefone(), is("63999978459"));
        assertThat(cli.nascimento(), is(LocalDate.of(1994,1,1)));
    }

    @Test
    void delete() {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(new EnderecoDTO("Rua 01, Qd 02, Lote 01", "Bairro algumaCoisa", "Palmas", "77777-777"));
        ClienteDTO dto = new ClienteDTO(
                "Janio Junior",
                "261.460.670-75",
                "janio@gmail.com",
                "12345678",
                "63999978459",
                LocalDate.of(1994,1,1),
                enderecos
        );

        ClienteResponseDTO cliente = clienteService.insert(dto);
        given()
                .when().delete("/clientes/" + cliente.id())
                .then()
                .statusCode(204);

        assertNull(clienteService.findById(cliente.id()));
    }

    @Test
    void findById() {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(new EnderecoDTO("Rua 01, Qd 02, Lote 01", "Bairro algumaCoisa", "Palmas", "77777-777"));
        ClienteDTO dto = new ClienteDTO(
                "Janio Junior",
                "261.460.670-75",
                "janio@gmail.com",
                "12345678",
                "63999978459",
                LocalDate.of(1994,1,1),
                enderecos
        );

        ClienteResponseDTO cliente = clienteService.insert(dto);

        given()
                .when().get("/clientes/search/id/" + cliente.id())
                .then()
                .statusCode(200)
                .body(
                        "id", is(cliente.id().intValue())
                );
    }

    @Test
    void findByNome() {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(new EnderecoDTO("Rua 01, Qd 02, Lote 01", "Bairro algumaCoisa", "Palmas", "77777-777"));
        ClienteDTO dto = new ClienteDTO(
                "Janio Junior",
                "261.460.670-75",
                "janio@gmail.com",
                "12345678",
                "63999978459",
                LocalDate.of(1994,1,1),
                enderecos
        );

        ClienteResponseDTO cliente = clienteService.insert(dto);

        given()
                .when().get("/clientes/search/nome/janio")
                .then()
                .statusCode(200)
                .body(
                        "id", hasItem(cliente.id().intValue())
                );
    }
}