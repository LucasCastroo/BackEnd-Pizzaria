package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.ClienteDTO;
import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.dto.EnderecoDTO;
import br.com.unitins.a1.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class ClienteResourceTest {

    @Inject
    ClienteService clienteService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/clientes")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(new EnderecoDTO("Rua 01, Qd 02, Lote 01", "Bairro algumaCoisa", "Palmas",  "77777-777"));
        ClienteDTO dto = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(11) 11111-1111",
                "1994-01-01",
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
                        "cpf", is("111.111.111-11"),
                        "email", is("janio@gmail.com"),
                        "telefone", is("(11) 11111-1111"),
                        "nascimento", is("1994-01-01")
                );
    }

    @Test
    public void testUpdate() {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        enderecos.add(new EnderecoDTO("Rua 01, Qd 02, Lote 01", "Bairro algumaCoisa", "Palmas", "77777-777"));
        ClienteDTO dto = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(11) 11111-1111",
                "1994-01-01",
                enderecos
        );

        ClienteResponseDTO clienteTest = clienteService.insert(dto);
        Long id = clienteTest.id();

        ClienteDTO dtoUpdate = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(22) 22222-2222",
                "1994-01-01",
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
        assertThat(cli.cpf(), is("111.111.111-11"));
        assertThat(cli.email(), is("janio@gmail.com"));
        assertThat(cli.telefone(), is("(22) 22222-2222"));
        assertThat(cli.nascimento(), is("1994-01-01"));
    }
}