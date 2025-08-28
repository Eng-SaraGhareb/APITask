package com.example;
import ApiBase.RequestApi;
import URLs.URLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class Delete {

    
    @Test
    public void DeleteBook_ValidId() {
        RequestApi api = new RequestApi(URLs.getBookByIdUrl(1));
        api.setHeader("no-auth", "true");
        Response response = api.sendRequest("DELETE")
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
    public void DeleteBook_InvalidIdFormat() {
        RequestApi api = new RequestApi(URLs.getBaseUrl() + "/invalid"); // 'invalid' is not an integer
        api.setHeader("no-auth", "true");
        Response response = api.sendRequest("DELETE")
            .then()
            .statusCode(400)
            .log()
            .all()
            .extract()
            .response();

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(statusCode, 400, "Expected status code 400 for invalid ID format");
    }
}
