package com.cybertek.day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomeWorkZipcode {

    @BeforeAll
    public static void init() {
        baseURI = "https://api.zippopotam.us/";
    }

    @Test
    public void test() {

        Response response = given().accept(ContentType.JSON).and().pathParam("zipcode", 22031).when().get("us/{zipcode}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        assertEquals("cloudflare", response.header("Server"));
        assertTrue(response.headers().hasHeaderWithName("Report-to"));
        JsonPath jsonPath = response.jsonPath();
        assertEquals(22031, jsonPath.getInt("'post code'"));
        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("US", jsonPath.getString("'country abbreviation'"));
        assertEquals("Fairfax", jsonPath.getString("places[0].'place name'"));
        assertEquals("Virginia", jsonPath.getString("places[0].state"));
        assertEquals(38.8604, jsonPath.getDouble("places[0].latitude"));
    }


    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).and().pathParam("zipcode", 50000).when().get("us/{zipcode}");
        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());

    }

    @Test
    public void test3() {


        Response response = given().accept(ContentType.JSON).and().pathParam("state", "va")
                .and().pathParam("city", "fairfax").when().get("us/{state}/{city}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        JsonPath jsonPath = response.jsonPath();
        assertEquals("US", jsonPath.getString("'country abbreviation'"));
        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("Fairfax", jsonPath.getString("'place name'"));
        List<String> allPlaceName = jsonPath.getList("places.'place name'");

        for (String placeName : allPlaceName) {
            assertTrue(placeName.contains("Fairfax"));
        }

        List<String> allPostcode = jsonPath.getList("places.'post code'");

        for (String postcode : allPostcode) {
            assertTrue(postcode.startsWith("22"));
        }
    }


}
