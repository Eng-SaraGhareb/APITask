// package APIs;

// import ApiBase.RequestApi;
// import URLs.URLs;
// import io.restassured.http.ContentType;
// import io.restassured.response.Response;



// public class BooksModule extends RequestApi{

//     public static Response loginInvest(){

//         URLs base = new URLs();

//         String baseUrl = base.getDepositBaseUrl();

//         String endPoint = URLs.investEndPoint();

//         String fullUrl = baseUrl + endPoint;

//         setRequestURL(fullUrl);

//         setContentType(ContentType.JSON);

//         setBody(endPoint, fullUrl);
//         setBody("id", "0");
//         setBody("idBook", "0");
//         setBody("url","string");

//         return getResponse("POST");


//     }
// }
