package com.cybertek.day03;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath extends SpartanTestBase {





    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */
    @DisplayName("GET one spartan with Path Method")
    @Test
    public void test1(){
        Response response= given().accept(ContentType.JSON).and().pathParam("id",10).when().get("/api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
           int id=response.path("id");
           String name=response.path("name");
           String gender=response.path("gender");
           long phone=response.path("phone");


           assertEquals(10,id);
           assertEquals("Lorenza",name);
           assertEquals("Female",gender);
           assertEquals(3312820936l,phone);
    }






    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");

       // response.prettyPrint();

        int firstId=response.path("id[0]");
        System.out.println("firstId = " + firstId);

         String name =response.path("name[0]");
        System.out.println("name = " + name);



    }
}
