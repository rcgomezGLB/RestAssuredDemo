package com.rcgomez.tests;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.CreateUserDTO;
import com.globant.automation.model.CreateUserResponseDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class PostUserTest extends TestRunner {

    @Test(testName = "Validate user creation")
    public void createUserTest() {
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .name("Diego")
                .job("Test Automation")
                .build();

        Response response = RequestBuilder.postRequest(getBaseUrl(), "/users", createUserDTO);
        CreateUserResponseDTO createUserResponseDTO = response.as(CreateUserResponseDTO.class);

        assertEquals(response.getStatusCode(), 201, "The status code doesn't match.");
        assertTrue(createUserResponseDTO.getId() > 0, "The id should be greater than 0");
        assertEquals(createUserResponseDTO.getJob(), createUserDTO.getJob(), "The job should match");
        assertEquals(createUserResponseDTO.getName(), createUserDTO.getName(), "The name should match");
        assertNotNull(createUserResponseDTO.getCreatedAt(), "the createdAt should not be null");
    }
}
