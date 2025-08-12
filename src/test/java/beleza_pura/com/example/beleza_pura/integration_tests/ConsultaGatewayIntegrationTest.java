package beleza_pura.com.example.beleza_pura.integration_tests;

import beleza_pura.com.example.beleza_pura.entities.StatusAgendamento;
import org.apache.http.HttpStatus;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

@Sql("/test_data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class ConsultaGatewayIntegrationTest {

    @LocalServerPort
    private int port;

    // IDs from test_data.sql
    private final String uuidProfissional01 = "660e8400-e29b-41d4-a716-446655440001";
    private final String uuidEspecialidade01 = "550e8400-e29b-41d4-a716-446655440001";
    private final String uuidEspecialidade02 = "550e8400-e29b-41d4-a716-446655440002";
    private final String uuidEstabelecimento01 = "770e8400-e29b-41d4-a716-446655440001";
    private final String uuidEstabelecimento02 = "770e8400-e29b-41d4-a716-446655440002";
    private final String uuidCliente01 = "880e8400-e29b-41d4-a716-446655440001";

    @BeforeAll
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    // GET Endpoints Tests
    @Test
    void testConsultarEstabelecimentoPorId() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/estabelecimento/" + uuidEstabelecimento01)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void testConsultarEstabelecimentoPorIdInexistente() {
        String uuidInexistente = "999e8400-e29b-41d4-a716-446655440999";

        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/estabelecimento/" + uuidInexistente)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void testConsultarProfissionalPorId() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/profissional/" + uuidProfissional01)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void testConsultarProfissionalPorIdInexistente() {
        String uuidInexistente = "999e8400-e29b-41d4-a716-446655440999";

        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/profissional/" + uuidInexistente)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void testConsultarAgendamentosPorCliente() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/cliente/" + uuidCliente01)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(-1)); // Accepts 0 or more results
    }

    @Test
    void testConsultarAgendamentosPorProfissional() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/profissional/" + uuidProfissional01)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(-1)); // Accepts 0 or more results
    }

    @Test
    void testConsultarAgendamentosPorStatusAgendado() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/status/AGENDADO")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(-1)); // Accepts 0 or more results
    }

    @Test
    void testConsultarAgendamentosPorStatusConfirmado() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/status/CONFIRMADO")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(-1)); // Accepts 0 or more results
    }

    @Test
    void testConsultarAgendamentosPorStatusCancelado() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/status/CANCELADO")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(-1)); // Accepts 0 or more results
    }

    @Test
    void testConsultarAgendamentosPorStatusConcluido() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/status/CONCLUIDO")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(-1)); // Accepts 0 or more results
    }

    @Test
    void testConsultarAgendamentosPorClienteEStatus() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/cliente/" + uuidCliente01 + "/status/AGENDADO")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(-1)); // Accepts 0 or more results
    }

    @Test
    void testConsultarAgendamentosPorProfissionalEStatus() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/profissional/" + uuidProfissional01 + "/status/CONFIRMADO")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", greaterThan(-1)); // Accepts 0 or more results
    }

    // Edge case tests
    @Test
    void testConsultarAgendamentosComClienteInexistente() {
        String uuidClienteInexistente = "999e8400-e29b-41d4-a716-446655440999";

        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/cliente/" + uuidClienteInexistente)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(0)); // Should return empty list
    }

    @Test
    void testConsultarAgendamentosComProfissionalInexistente() {
        String uuidProfissionalInexistente = "999e8400-e29b-41d4-a716-446655440999";

        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/profissional/" + uuidProfissionalInexistente)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", equalTo(0)); // Should return empty list
    }

    @Test
    void testConsultarAgendamentosComStatusInvalido() {
        given().filter(new AllureRestAssured())
                .when()
                .get("/consulta/agendamentos/status/INVALID_STATUS")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST); // Should return 400 for invalid enum
    }
}