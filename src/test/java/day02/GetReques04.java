package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetReques04 {
    @Test
    public void test01() {

      /*
http://dummy.restapiexample.com/api/v1/employees  url’ine
GET request’i yolladigimda gelen response’un
status kodunun 200 ve content type’inin “application/json”
ve employees sayisinin 24
ve employee’lerden birinin “Ashton Cox”
ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
     */

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        Response response = given().when().get(url);
    //    response.prettyPrint();

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        System.out.println(response.contentType());

        response.then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType(ContentType.JSON);

        //employees sayisinin 24 olduğunu doğrulayın
        //ve employee’lerden birinin “Ashton Cox” olduğunu doğrulayın
        //ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
        response.then().assertThat().body("data",hasSize(24)
                ,"data.employee_name",hasItem("Ashton Cox")
                ,"data.employee_age",hasItems(21,61,23));


    }




}
/*
Matchers.hasSize(): Datanın size'ını doğrulamak için kullanılır.
Matchers.hasItem(): Girilen tek bir data'yı doğrulamak için kullanılır.
Matchers.hasItems(): Girilen birden fazla datayı doğrulamak için kullanılır.


hasItem ==Öğe var
hasItems ==Öğeler var
hasSize ==Uzunluğu

 */
