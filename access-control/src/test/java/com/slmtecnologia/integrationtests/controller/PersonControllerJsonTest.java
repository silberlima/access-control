package com.slmtecnologia.integrationtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slmtecnologia.configs.TestConfigs;
import com.slmtecnologia.integrationtests.dto.PersonDto;
import com.slmtecnologia.integrationtests.testcontainers.AbstractIntegrationTest;
import com.slmtecnologia.model.dto.AuthenticationRequest;
import com.slmtecnologia.model.dto.AuthenticationResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static final String NAME = "João";
    private static final String CPF = "12345678900";
    private static final String EMAIL = "joao@gmail.com";
    private static final String BASE_PATH = "/api/person";
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonDto personDto;
    @BeforeAll
    public static void setup(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        personDto = new PersonDto();
    }

    private void mockPerson(){
        personDto.setCpf(CPF);
        personDto.setEmail(EMAIL);
        personDto.setName(NAME);
    }

    @Test
    @Order(0)
    void authorization() throws JsonProcessingException {
        AuthenticationRequest user = new AuthenticationRequest("person@mail.com", "passoword");

        var accessToken = given()
                .basePath("api/auth/authenticate")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                    .when()
                .post()
                    .then()
                        .statusCode(200)
                            .extract()
                            .body()
                                .as(AuthenticationResponse.class)
                            .accessToken();
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer "+accessToken)
                .setBasePath(BASE_PATH)
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

    }
    @Test
    @Order(1)
    void testCreate() throws JsonProcessingException {
        mockPerson();
        var content =  given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                    .body(personDto)
                .when()
                    .post()
                .then()
                    .statusCode(201)
                     .extract()
                        .body()
                            .asString();
        PersonDto persistedPerson = objectMapper.readValue(content, PersonDto.class);
        personDto = persistedPerson;

        assertNotNull(persistedPerson);
        assertEquals(NAME, persistedPerson.getName());
        assertTrue(persistedPerson.getId() > 0);
    }

    @Test
    @Order(2)
    void testCreateWithWrongOrigin()  {

        var content =  given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SLMTECNOLOGIA)
                    .body(personDto)
                .when()
                    .post()
                .then()
                    .statusCode(403)
                        .extract()
                            .body()
                                .asString();

        assertNotNull(content);
        assertEquals("Invalid CORS request", content);

    }

    @Test
    @Order(3)
    void testFindById() throws JsonProcessingException {

        var content =  given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                    .pathParam("id", personDto.getId())
                .when()
                    .get("{id}")
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();
        PersonDto persistedPerson = objectMapper.readValue(content, PersonDto.class);
        personDto = persistedPerson;

        assertNotNull(persistedPerson);
        assertEquals(NAME, persistedPerson.getName());
        assertTrue(persistedPerson.getId() > 0);
    }


    @Test
    @Order(4)
    void testFindByIdWithWrong()  {


        var content =  given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .pathParam("id", personDto.getId())
                .when()
                    .get("{id}")
                .then()
                    .statusCode(403)
                        .extract()
                            .body()
                                .asString();

        assertNotNull(content);
        assertEquals("Invalid CORS request", content);

    }
}

