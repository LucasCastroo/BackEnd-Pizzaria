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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class FuncionarioResourceTest {
    @Inject
    FuncionarioService funcionarioService;

    @Test
    public void testInsert() {
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
    public void testUpdate() {
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

        FuncionarioResponseDTO fun = funcionarioService.findById(6l);
        assertThat(fun.nome(), is("Mailson"));
        assertThat(fun.cpf(), is("222.222.222-22"));
        assertThat(fun.email(), is("mailson@gmail.com"));
        assertThat(fun.nascimento(), is("1986-07-18"));
        assertThat(fun.tipoAcesso(), is(NivelAcesso.ADMIN));
    }
}
