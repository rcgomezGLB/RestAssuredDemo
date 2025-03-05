package com.rcgomez.tests;

import com.rcgomez.config.TestRunner;
import com.rcgomez.model.ListPetsDTO;
import com.rcgomez.model.PetDTO;
import com.rcgomez.request.RequestBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetEndpointTest extends TestRunner {

    @Test(testName = "Find pets by status")
    public void findByStatus() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("status", "available");

        Response response = RequestBuilder.getRequestWithParams(getBaseUrl(),"/pet/findByStatus",queryParams);
        List<PetDTO> retrievedList = List.of(response.as(PetDTO[].class));

        Assert.assertEquals(response.getStatusCode(),200);

        // Assert that all retrieved pets have available status
        for (PetDTO pet: retrievedList) {
            Assert.assertEquals(pet.getStatus(),"available");
        }
    }
}
