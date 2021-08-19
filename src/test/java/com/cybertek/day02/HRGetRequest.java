package com.cybertek.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HRGetRequest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://54.152.179.245:1000/ords/hr";
    }


@DisplayName("Get request to /regions")
    @Test
    public void test1(){
        Response response= RestAssured.get("/regions");

    System.out.println("response.prettyPrint() = " + response.prettyPrint());
}


/*
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
     */

    @Test
    public void test2(){
        Response response= RestAssured.given().accept(ContentType.JSON).when().get("/regions/2");
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Americas"));
    }
}
