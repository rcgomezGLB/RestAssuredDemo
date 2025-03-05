package com.rcgomez.tests;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.GetUserResponseDTO;
import com.globant.automation.model.UserDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

@Slf4j
public class GetUserTest extends TestRunner {

    @Test(testName = "Validate found user - 1")
    public void userFoundTestAssertion1() {
        RestAssured.given()
                .baseUri("https://reqres.in/api")
                .header("Content-Type", "application/json")
                .when()
                .get("/users/2")
                .then()
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
    }

    @Test(testName = "Validate found user - 2")
    public void userFoundTestAssertion2() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/users/2");
        JsonPath jsonPath = response.jsonPath();

        log.info("Llamando al servicio de get usuarios");

        Integer id = jsonPath.getInt("data.id");
        String firstName = jsonPath.getString("data.first_name");
        String lastName = jsonPath.getString("data.last_name");

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match for the /users/2");
        assertEquals(id, 2, "The id should match");
        assertEquals(firstName, "Janet", "The firstName should match");
        assertEquals(lastName, "Weaver", "The lastName should match");
    }

    @Test(testName = "Validate found user - 3")
    public void userFoundTestAssertion3() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/users/2");
        GetUserResponseDTO getUserResponseDTO = response.as(GetUserResponseDTO.class);

        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        assertEquals(getUserResponseDTO.getUserDTO().getId(), 2, "The id should match");
        assertEquals(getUserResponseDTO.getUserDTO().getFirstName(), "Janet", "The firstName should match");
        assertEquals(getUserResponseDTO.getUserDTO().getLastName(), "Weaver", "The lastName should match");
    }

    @Test(testName = "Validate found user - 4")
    public void userFoundTestAssertion4() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/users/2");
        GetUserResponseDTO expectedResponse = GetUserResponseDTO.builder()
                .userDTO(UserDTO.builder()
                        .id(2)
                        .firstName("Janet")
                        .lastName("Weaver")
                        .email("janet.weaver@reqres.in")
                        .avatar("https://reqres.in/img/faces/2-image.jpg")
                        .build())
                .build();

        GetUserResponseDTO getUserResponseDTO = response.as(GetUserResponseDTO.class);
        assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        assertEquals(expectedResponse, getUserResponseDTO, "The user should match");
    }

    @Test(testName = "Validate user not found")
    public void userNotFoundTest() {
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/users/9999");
        GetUserResponseDTO userResponseDTO = response.as(GetUserResponseDTO.class);

        assertEquals(response.getStatusCode(), 404, "The status code doesn't match.");
        assertNull(userResponseDTO.getUserDTO(), "The user should not exist.");
    }
}
