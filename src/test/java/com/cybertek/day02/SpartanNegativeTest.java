package com.cybertek.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeTest {

    @BeforeAll
    public static void init(){
    baseURI="http://54.152.179.245:8000";

    }

 /*TASK
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml
    */

    @Test
    public void test1(){
        Response response= RestAssured.given().accept(ContentType.XML).when().get("/api/spartans/10 end point");

       assertEquals(406,response.statusCode());

        assertEquals("application/xml;charset=UTF-8",response.contentType());

    }

}
