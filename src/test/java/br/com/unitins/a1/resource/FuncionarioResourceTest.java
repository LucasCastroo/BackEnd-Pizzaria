package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.*;
import br.com.unitins.a1.model.Funcionario;
import br.com.unitins.a1.model.NivelAcesso;
import br.com.unitins.a1.service.FuncionarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class FuncionarioResourceTest {
    @Inject
    FuncionarioService funcionarioService;

    @Test
    public void insert() {
        Funcionario dto = new Funcionario(
                "Mailson",
                "222.222.222-22",
                "mailson@gmail.com",
                "222222",
                "1986-07-18",
                NivelAcesso.GERENTE
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/funcionarios")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Mailson"),
                        "cpf", is("222.222.222-22"),
                        "email", is("mailson@gmail.com"),
                        "nascimento", is("1986-07-18"),
                        "tipoAcesso", is(NivelAcesso.GERENTE.name())
                );
    }

    @Test
    public void update() {
        FuncionarioDTO dto = new FuncionarioDTO(
                "Mailson",
                "222.222.222-22",
                "mailson@gmail.com",
                "222222",
                "1986-07-18",
                NivelAcesso.GERENTE
        );

        FuncionarioResponseDTO funcionarioTest = funcionarioService.insert(dto);
        Long id = funcionarioTest.id();

        FuncionarioDTO dtoUpdate = new FuncionarioDTO(
                "Mailson",
                "222.222.222-22",
                "mailson@gmail.com",
                "222222",
                "1986-07-18",
                NivelAcesso.ADMIN
        );

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/funcionarios/"+ id)
                .then()
                .statusCode(204);

        FuncionarioResponseDTO fun = funcionarioService.findById(id);
        assertThat(fun.nome(), is("Mailson"));
        assertThat(fun.cpf(), is("222.222.222-22"));
        assertThat(fun.email(), is("mailson@gmail.com"));
        assertThat(fun.nascimento(), is("1986-07-18"));
        assertThat(fun.tipoAcesso(), is(NivelAcesso.ADMIN));
    }

    @Test
    void delete() {
        FuncionarioDTO dto = new FuncionarioDTO(
                "Mailson",
                "222.222.222-22",
                "mailson@gmail.com",
                "222222",
                "1986-07-18",
                NivelAcesso.GERENTE
        );

        FuncionarioResponseDTO funcionario = funcionarioService.insert(dto);
        given()
                .when().delete("/funcionarios/" + funcionario.id())
                .then()
                .statusCode(204);

        assertNull(funcionarioService.findById(funcionario.id()));
    }

    @Test
    void findById() {
        FuncionarioDTO dto = new FuncionarioDTO(
                "Mailson",
                "222.222.222-22",
                "mailson@gmail.com",
                "222222",
                "1986-07-18",
                NivelAcesso.GERENTE
        );

        FuncionarioResponseDTO funcionario = funcionarioService.insert(dto);

        given()
                .when().get("/funcionarios/search/id/" + funcionario.id())
                .then()
                .statusCode(200)
                .body(
                        "id", is(funcionario.id().intValue())
                );
    }

    @Test
    void findByNome() {
        FuncionarioDTO dto = new FuncionarioDTO(
                "Mailson",
                "222.222.222-22",
                "mailson@gmail.com",
                "222222",
                "1986-07-18",
                NivelAcesso.GERENTE
        );

        FuncionarioResponseDTO funcionario = funcionarioService.insert(dto);

        given()
                .when().get("/funcionarios/search/nome/mailson")
                .then()
                .statusCode(200)
                .body(
                        "id", hasItem(funcionario.id().intValue())
                );
    }
}
