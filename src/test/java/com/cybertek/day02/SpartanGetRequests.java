package com.cybertek.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String baseURL = "http://54.152.179.245:8000";

//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must be 200
//    And response Content Type must be application/json
//    And response body should include spartan result

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseURL + "/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());


        System.out.println("response.contentType() = " + response.contentType());


        System.out.println("response.prettyPrint() = " + response.prettyPrint());

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");


    }
        /*
        Given accept is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseURL + "/api/spartans/3");

        Assertions.assertEquals( 200,response.statusCode());

        Assertions.assertEquals( "application/json",response.contentType());

     Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }

/*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @Test
    public void test3(){
        Response response= RestAssured.get(baseURL+"/api/hello");

        Assertions.assertEquals( 200,response.statusCode());

        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        Assertions.assertEquals("17",response.header("Content-Length"));

        Assertions.assertEquals("Hello from Sparta",response.body().asString());




    }

}