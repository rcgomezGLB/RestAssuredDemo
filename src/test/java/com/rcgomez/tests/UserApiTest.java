package com.rcgomez.tests;

import com.rcgomez.config.TestRunner;
import com.rcgomez.model.UserDTO;
import com.rcgomez.request.RequestBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class UserApiTest extends TestRunner {

    private UserDTO userDTO;

    @BeforeClass
    public void setUp() {
        // Create random unique username
        String username = "testuser_" + UUID.randomUUID();

        Random random = new Random();
        long id = random.nextLong(100000);

        userDTO = UserDTO.builder()
                .id(id)
                .firstName("Test")
                .lastName("User")
                .username(username)
                .phone("100000")
                .email("email@gmail.com")
                .password("password")
                .userStatus(0)
                .build();
    }

    // This test fails randomly
    @Test(testName = "User is created successfully")
    public void createUserTest() {
        Response createResponse = RequestBuilder.postRequest(getBaseUrl(), "/user", userDTO);
        Response getResponse = RequestBuilder.getRequest(getBaseUrl(),"/user/" + userDTO.getUsername());
        UserDTO userResponseDTO = getResponse.as(UserDTO.class);

        Assert.assertEquals(createResponse.getStatusCode(), 200);
        Assert.assertEquals(getResponse.getStatusCode(),200); // This returns 404 randomly
        Assert.assertEquals(userResponseDTO,userDTO);
    }

    @Test(testName = "User is logged in successfully", dependsOnMethods = {"createUserTest"})
    public void logInTest() {

        Map<String, String> queryParams = new HashMap<>();

        queryParams.put("username", userDTO.getUsername());
        queryParams.put("password", userDTO.getPassword());

        Response response = RequestBuilder.getRequestWithParams(getBaseUrl(),"/user/login",queryParams);
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(jsonPath.getString("message").contains("logged in"));
    }

    @Test(testName = "User is logged out successfully", dependsOnMethods = {"logInTest"})
    public void logOutTest(){
        Response response = RequestBuilder.getRequest(getBaseUrl(),"/user/logout");
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
