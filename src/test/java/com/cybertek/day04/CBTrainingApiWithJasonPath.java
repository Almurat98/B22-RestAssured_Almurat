package com.cybertek.day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class CBTrainingApiWithJasonPath {
    @BeforeAll
    public static void init() {
        baseURI = "http://api.cybertektraining.com";
    }



    @DisplayName("GET request to individual student")
    @Test
    public void test(){
        //send a get request to student id 23401 as a path parameter and accept header application/json
        Response response = given().accept(ContentType.JSON).and().pathParam("id",23401).when().get(baseURI+"/student/{id}");
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertEquals("gzip",response.header("Content-Encoding"));
        //verify Date header exists
        assertTrue(response.headers().toString().contains("Date"));
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */
        JsonPath jsonPath= response.jsonPath();
//        jsonPath.prettyPrint();
        String firstName = jsonPath.getString("students[0].firstName");
        int batch = jsonPath.getInt("students[0].batch");
        int section = jsonPath.getInt("students[0].section");
        String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
        String companyName = jsonPath.getString("students[0].company.companyName");
        String state = jsonPath.getString("students[0].company.address.state");
        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");

        assertEquals("Vera",firstName);
        assertEquals(14,batch);
        assertEquals(12,section);
        assertEquals("aaa@gmail.com",emailAddress);
        assertEquals("Cybertek",companyName);
        assertEquals("IL",state);
        assertEquals(60606,zipCode);

    }











}
