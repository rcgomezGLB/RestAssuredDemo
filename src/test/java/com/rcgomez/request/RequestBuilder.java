package com.rcgomez.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.NonNull;

import java.util.Map;

public class RequestBuilder {

    private static RequestSpecification baseRequest(String baseUrl) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
    }

    public static Response getRequest(String baseUrl, String path) {
        return baseRequest(baseUrl).get(path);
    }

    public static Response postRequest(String baseUrl, String path, Object body) {
        return baseRequest(baseUrl).body(body).post(path);
    }

    public static Response getRequestWithParams(String baseUrl, String path, @NonNull Map<String, String> queryParams) {
        RequestSpecification requestSpecification = baseRequest(baseUrl);

        // Add query parameters
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            requestSpecification.queryParam(entry.getKey(), entry.getValue());
        }

        // Execute the GET request
        return requestSpecification.get(path);
    }
}
