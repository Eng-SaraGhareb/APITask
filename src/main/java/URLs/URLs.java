package URLs;

public class URLs {

    public static String getBaseUrl() {
        return "https://fakerestapi.azurewebsites.net/api/v1/Books";
    }

    public static String getBookByIdUrl(int id) {
        return getBaseUrl() + "/" + id;
    }

    public static String getBooksUrl() {
        return getBaseUrl();
    }
}