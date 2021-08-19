package com.cybertek.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url="http://3.239.250.93:8000/api/spartans";
    @Test
    public void test1(){

        Response response= RestAssured.get(url);
        System.out.println(response.statusCode());

        response.prettyPrint();

    }
}
