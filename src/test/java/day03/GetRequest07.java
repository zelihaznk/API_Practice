package day03;

import utilities.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.*;

public class GetRequest07 extends GMIBankBaseUrl {

    /*
    http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın
   “firstName”: “Melva”,
   “lastName”: “Bernhard”,
   “email”: “chas.kuhlman@yahoo.com”
   “zipCode”: “40207"
   “country” “name”: “San Jose”
   “login”: “delilah.metz”

   {
    "firstName": "Melva",
    "lastName": "Bernhard",
    "email": "chas.kuhlman@yahoo.com"
    "zipCode": "40207",
    "country": {
        "name": "San Jose",
    },
     */

    @Test
    public void test01() {

        //1. Set The URL
        spec.pathParams("first","tp-customers","second",110472);

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response
      //  Response response = given().spec(spec).headers("Authorization","Bearer " + Authentication.generateToken()).when().get("/{first}/{second}");


        Response response = given().spec(spec).headers("Authorization","Bearer "+generateToken()) //Bu kısımda token tanımlamadan yapılan çağırma işlemleri hata alır.
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion

        //Matchers ile doğrula
        response.then().assertThat()
                .body("firstName",equalTo("Melva")
                   ,"lastName",equalTo("Bernhard")
                   ,"email",equalTo("chas.kuhlman@yahoo.com")
                   ,"zipCode",equalTo("40207")
                   ,"country.name",equalTo("San Jose")
                   ,"user.login",equalTo("delilah.metz"));



        //JsonPath ile doğrula
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Melva",jsonPath.getString("firstName"));
        assertEquals("Bernhard",jsonPath.getString("lastName"));
        assertEquals("chas.kuhlman@yahoo.com",jsonPath.getString("email"));
        assertEquals("40207",jsonPath.getString("zipCode"));
        assertEquals("San Jose",jsonPath.getString("country.name"));
        assertEquals("delilah.metz",jsonPath.getString("user.login"));




    }
}
