package day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;

public class GetReques06 extends Authentication {

    @Test
    public void test06() {

        String url = "https://www.gmibank.com/api/tp-customers/114351";


        Response response = given().headers("Authorization","Bearer " +generateToken()).when().get(url);
        //Bearer den sonra boşluk bırakmayı unutma!!

        response.prettyPrint();


        //Matcher class ile müşteri bilgilerini doğrulayın
        response.then().assertThat().body("firstName",equalTo("Della")
                ,"lastName",equalTo("Heaney")
                ,"email",equalTo("ricardo.larkin@yahoo.com")
                ,"mobilePhoneNumber",equalTo("123-456-7893")
                ,"accounts[0].balance",equalTo(11190)
                ,"accounts[0].accountType",equalTo("CHECKING")
                ,"accounts[1].balance",equalTo(69700)
                ,"accounts[1].accountType",equalTo("CREDIT_CARD"));


        //JsonPath ile müşteri bilgilerini doğrulayın
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Della",jsonPath.getString("firstName"));
        assertEquals("Heaney",jsonPath.getString("lastName"));
        assertEquals("ricardo.larkin@yahoo.com",jsonPath.getString("email"));
        assertEquals("123-456-7893",jsonPath.getString("mobilePhoneNumber"));
        assertEquals(11190,jsonPath.getInt("accounts[0].balance"));
        assertEquals("CHECKING",jsonPath.getString("accounts[0].accountType"));
        assertEquals(69700,jsonPath.getInt("accounts[1].balance"));
        assertEquals("CREDIT_CARD",jsonPath.getString("accounts[1].accountType"));


    }
}
/*
import static utilities.Authentication.generateToken;

extends etmek yerine import edilerek te kullanılabilirdi
 */
