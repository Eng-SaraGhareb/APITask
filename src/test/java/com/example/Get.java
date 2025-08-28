package com.example;

import ApiBase.RequestApi;
import URLs.URLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class Get {

    
    @Test
    public void GetBooks_Valid() {
        RequestApi api = new RequestApi(URLs.getBooksUrl());
        api.setHeader("no-auth", "true");
        Response response = api.sendRequest("GET")
            .then()
            .statusCode(200)
            .log()
            .all()
            .extract()
            .response();

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(statusCode, 200, "Expected status code 200");
    }

    
    @Test
    public void GetBooks_InvalidEndpoint() {
        RequestApi api = new RequestApi(URLs.getBaseUrl() + "z"); // Typo in endpoint
        api.setHeader("no-auth", "true");
        Response response = api.sendRequest("GET")
            .then()
            .statusCode(404)
            .log()
            .all()
            .extract()
            .response();

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(statusCode, 404, "Expected status code 404 for invalid endpoint");
    }
}
