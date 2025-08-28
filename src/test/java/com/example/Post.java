package com.example;

import ApiBase.RequestApi;
import URLs.URLs;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import java.io.FileReader;
import java.io.IOException;

public class Post {

    
    @Test
    public void PostBook_Valid() throws IOException, ParseException {
        String path = System.getProperty("user.dir") + "\\src\\resources\\body.json";
        JSONParser parser = new JSONParser();
        JSONObject request;
        try (FileReader reader = new FileReader(path)) {
            request = (JSONObject) parser.parse(reader);
        }

        RequestApi api = new RequestApi(URLs.getBooksUrl());
        api.setHeader("no-auth", "true");
        api.setHeader("Content-Type", "application/json");
        api.setBody(request);

        Response response = api.sendRequest("POST")
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
    public void PostBook_MissingFields() {
        JSONObject request = new JSONObject();

        RequestApi api = new RequestApi(URLs.getBooksUrl());
        api.setHeader("no-auth", "true");
        api.setHeader("Content-Type", "application/json");
        api.setBody(request);

        Response response = api.sendRequest("POST")
            .then()
            .statusCode(400)
            .log()
            .all()
            .extract()
            .response();

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(statusCode, 400, "Expected status code 400 for empty body");
    }
}

