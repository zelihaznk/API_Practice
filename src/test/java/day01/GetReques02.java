package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetReques02 {

    @Test
    public void test02() {

        String url = "https://reqres.in/api/users";

        Response response = given().when().get(url);
        //given().when().get(url) -> end point'e göndermek için request oluşturmuş olduk.
        //Response response -> api tarafından bana dönen response (cevap)

        //Headers test
         response.then()
                 .assertThat()
                 .statusCode(200)
                 .statusLine("HTTP/1.1 200 OK")
                 .contentType(ContentType.JSON);

         //Body Test
        /* id'si 1 olanın datalarının aşağıdaki gibi olduğunu test ediiniz
        "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
         */
        //data[0] idsi 1 olanı verir

         // 3)  Matcher class ile
        // 1. yol
        response.then().body("data[0].email", equalTo("george.bluth@reqres.in")
                ,"data[0].first_name", equalTo("George")
                ,"data[0].last_name", equalTo("Bluth"));

        // 2. yol
        response.then().body("data[0].email", equalTo("george.bluth@reqres.in")
                ,"data[0].first_name",equalTo("George")
                ,"data[0].last_name",equalTo("Bluth"));










    }
}
