package com.cybertek.day04;

import com.cybertek.utilities.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class ORDSApiPathTest extends ORDSTestBase{


    @DisplayName("GET request to countries with Path method")
    @Test
    public void test(){

        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}").get("/countries");


        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);


        List<Integer> allRegionID= response.path("items.region_id");
        for (Integer each : allRegionID) {
            System.out.println(each);
            assertEquals(2,each);
        }
    }


    @Test
    public void test2(){
        Response response=given().and().queryParam("q","{\"job_id\":\"IT_PROG\"}").when().get("employees");



        List<String>allJobID=response.path("items.job_id");

        for (String each : allJobID) {
            System.out.println("each = " + each);
            assertEquals("IT_PROG",each);



        }




    }








}
