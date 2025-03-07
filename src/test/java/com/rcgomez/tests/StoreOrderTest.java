package com.rcgomez.tests;

import com.rcgomez.config.TestRunner;
import com.rcgomez.model.OrderDTO;
import com.rcgomez.request.RequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class StoreOrderTest extends TestRunner {

    @Test(testName = "Successfully creates an order")
    public void createOrder() {

        Random random = new Random();

        OrderDTO orderDTO = OrderDTO.builder()
                .id(random.nextLong(1000000))
                .petId(1L)
                .quantity(1L)
                .shipDate("2025-03-06T16:49:51.926Z")
                .status("placed")
                .complete(false)
                .build();

        Response responseCreated = RequestBuilder.postRequest(getBaseUrl(),"/store/order", orderDTO);
        Response responseGET = RequestBuilder.getRequest(getBaseUrl(),"/store/order/" + orderDTO.getId());
        OrderDTO orderDTOGET = responseGET.as(OrderDTO.class);

        Assert.assertEquals(responseCreated.getStatusCode(),200);
        Assert.assertEquals(orderDTOGET.getId(),orderDTO.getId());
        Assert.assertEquals(orderDTOGET.getPetId(),orderDTO.getPetId());
    }
}
