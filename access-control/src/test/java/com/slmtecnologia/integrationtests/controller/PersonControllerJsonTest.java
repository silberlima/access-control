package com.slmtecnologia.integrationtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slmtecnologia.configs.TestConfigs;
import com.slmtecnologia.integrationtests.dto.PersonDto;
import com.slmtecnologia.integrationtests.testcontainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static final String BASE_PATH = "/api/person";
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonDto personDto;
    @BeforeAll
    public static void setup(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Test
    @Order(1)
    void testCreate() throws JsonProcessingException {
        personDto = new PersonDto(null, "João", "14725836900", "teste@gmail.com");
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SLMTECNOLOGIA)
                .setBasePath(BASE_PATH)
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
        var content =  given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(personDto)
                    .when()
                    .post()
                .then()
                    .statusCode(201)
                     .extract()
                        .body()
                            .asString();
        PersonDto createPerson = objectMapper.readValue(content, PersonDto.class);
        personDto = createPerson;

        assertNotNull(createPerson);
        assertTrue(createPerson.id() > 0);
    }
}
