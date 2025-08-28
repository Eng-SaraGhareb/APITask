package ApiBase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class RequestApi {

    protected RequestSpecification request;
    protected Response response;
    protected String requestUrl;
    protected File multiPartRequest;
    protected Map<String, String> headers;
    protected Map<String, String> formParams;
    protected Map<String, String> queryParams;
    protected JSONObject body;
    protected ContentType contentType;

    public RequestApi(String url) {
        this.requestUrl = url;
        this.headers = new HashMap<>();
        this.formParams = new HashMap<>();
        this.queryParams = new HashMap<>();
        this.body = new JSONObject();
        this.contentType = null;
    }

    public void setQueryParam(String key, String value) { queryParams.put(key, value); }
    public void setFormParam(String key, String value) { formParams.put(key, value); }
    public void setHeader(String key, String value) { headers.put(key, value); }
    @SuppressWarnings("unchecked")
    public void setBody(String key, Object value) { body.put(key, value); }
    public void setBody(JSONObject jsonObject) { this.body = jsonObject; }
    public void setMultiPartRequest(File file) { multiPartRequest = file; }
    public void setContentType(ContentType value) { contentType = value; }

    public Response sendRequest(String requestType) {
        request = given().log().all();

        if (contentType != null) { request.contentType(contentType); }
        if (!headers.isEmpty()) { request.headers(headers); }
        if (!formParams.isEmpty()) { request.formParams(formParams); }
        if (!queryParams.isEmpty()) { request.queryParams(queryParams); }
        if (!body.isEmpty()) { request.body(body.toString()); }
        if (multiPartRequest != null) { request.multiPart("file", multiPartRequest); }

        switch (RequestType.valueOf(requestType)) {
            case POST -> response = request.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT))).when().post(requestUrl).andReturn();
            case PATCH -> response = request.when().patch(requestUrl).andReturn();
            case PUT -> response = request.when().put(requestUrl).andReturn();
            case GET -> response = request.when().get(requestUrl).andReturn();
            case DELETE -> response = request.when().delete(requestUrl).andReturn();
            default -> {}
        }
        return response;
    }

    private enum RequestType {
        POST, GET, PUT, DELETE, PATCH
    }
}
