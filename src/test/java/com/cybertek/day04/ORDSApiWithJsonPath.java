package com.cybertek.day04;

import com.cybertek.utilities.ORDSTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends ORDSTestBase {

    @DisplayName("GET request from countries")
    @Test
    public void test(){
        Response response=get("countries");
        JsonPath jsonPath= response.jsonPath();
        String secondCountry=jsonPath.getString("items[1].country_name");
        System.out.println("secondCountry = " + secondCountry);

        List<String>allCountries=jsonPath.getList("items.country_name");
        System.out.println("allCountries = " + allCountries);


        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==3}.country_name");
        System.out.println(countryNameWithRegionId2);
    }

    @DisplayName("GET requesto /employees with query param")
    @Test
    public void test2(){
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        //get me first name of employees who is making more than 10000

        List<String>firstName= jsonPath.getList("items.findAll{it.salary>=10000}.first_name");
        System.out.println("firstName = " + firstName);


        //get the max salary first_name
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String kingNameWithPathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);
    }




    @Test
    public void test3(){

}


}
