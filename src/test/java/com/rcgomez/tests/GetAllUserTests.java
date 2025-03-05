package com.rcgomez.tests;

import com.globant.automation.config.TestRunner;
import com.globant.automation.model.GetAllUsersResponseDTO;
import com.globant.automation.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class GetAllUserTests extends TestRunner {

    @Test(testName = "Validate get all users")
    public void getAllUsersTest(){
        Response response = RequestBuilder.getRequest(getBaseUrl(), "/users");
        GetAllUsersResponseDTO getAllUsersResponseDTO = response.as(GetAllUsersResponseDTO.class);
        List<Map> users = response.jsonPath().get("data.findAll { user -> user.id >= 5 }");
        String lastRecordFirstName = response.jsonPath().getString("data[-1].first_name");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "The status code doesn't match.");
        softAssert.assertEquals(getAllUsersResponseDTO.getTotal(), 12, "The total records should match.");
        softAssert.assertEquals(lastRecordFirstName, "Tracey", "The last record first_name should match.");
        softAssert.assertTrue(users.size() == 2, "Only two user should be returned.");

        softAssert.assertAll();
    }
}
