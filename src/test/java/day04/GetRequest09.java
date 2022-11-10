package day04;

import utilities.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Country;
import pojos.Customer;
import pojos.User;
import utilities.JsonUtil;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;


public class GetRequest09 extends GMIBankBaseUrl {

    /*
    http://www.gmibank.com/api/tp-customers/110452
     */


    //EXPECTED DATA
        /*
            /*
        "user": {
        "id": 110016,
        "login": "leopoldo.reinger",
        "firstName": "Jasmine",
        "lastName": "Stehr",
        "email": "marni.zboncak@yahoo.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    }
     */

    @Test
    public void test09(){

        //1. Set The URL
        spec.pathParams("first", "tp-customers", "second", 110452);
        // 2. Set The Expected Data ( put, post, patch)
        User user = new User(110016,"leopoldo.reinger", "Jasmine", "Stehr",
                "marni.zboncak@yahoo.com", true, "en", null, null);

        Country country = new Country(3, "USA", null);

        Customer expectedData = new Customer(110452, "Jasmine", "Stehr", "V", "marni.zboncak@yahoo.com"
                , "463-609-2097", "1-112-497-0270", "16525", "14387 Al Ridge5343 Bert Burgs","Waltermouth"
                , "761-59-2911", "2021-11-28T21:00:00Z", false, country, "California", user);



        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).headers("Authorization","Bearer "+generateToken()).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion
        Customer actualData = response.as(Customer.class);

        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getMiddleInitial(),actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(),actualData.getMobilePhoneNumber());
        assertEquals(expectedData.getPhoneNumber(),actualData.getPhoneNumber());
        assertEquals(expectedData.getZipCode(),actualData.getZipCode());
        assertEquals(expectedData.getAddress(),actualData.getAddress());
        assertEquals(expectedData.getCity(),actualData.getCity());
        assertEquals(expectedData.getSsn(),actualData.getSsn());
        assertEquals(expectedData.getCreateDate(),actualData.getCreateDate());


        assertEquals(expectedData.getCountry().getId(),actualData.getCountry().getId());
        assertEquals(expectedData.getCountry().getName(),actualData.getCountry().getName());
        assertEquals(expectedData.getCountry().getStates(),actualData.getCountry().getStates());
        assertEquals(expectedData.getState(),actualData.getState());
        assertEquals(expectedData.getUser().getId(),actualData.getUser().getId());
        assertEquals(expectedData.getUser().getLogin(),actualData.getUser().getLogin());
        assertEquals(expectedData.getUser().getFirstName(),actualData.getUser().getFirstName());
        assertEquals(expectedData.getUser().getLastName(),actualData.getUser().getLastName());
        assertEquals(expectedData.getUser().getEmail(),actualData.getUser().getEmail());
        assertEquals(expectedData.getUser().getActivated(),actualData.getUser().getActivated());
        assertEquals(expectedData.getUser().getLangKey(),actualData.getUser().getLangKey());
        assertEquals(expectedData.getUser().getImageUrl(),actualData.getUser().getImageUrl());
        assertEquals(expectedData.getUser().getResetDate(),actualData.getUser().getResetDate());

        //Object Mapper ile
        Customer actualData2 = JsonUtil.convertJsonToJava(response.asString(),Customer.class);

        assertEquals(expectedData.getId(), actualData2.getId());
        assertEquals(expectedData.getUser().getLogin(), actualData2.getUser().getLogin());
        assertEquals(expectedData.getCountry().getName(), actualData2.getCountry().getName());





    }
}