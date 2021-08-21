package com.cybertek.day05;

import com.cybertek.utilities.ORDSTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSHamcrestTest extends ORDSTestBase {
    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest(){
        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)


        given().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("employees").then().statusCode(200).and().contentType("application/json")
                .and().body("items.job_id",everyItem(equalTo("IT_PROG")))
                .and().body("items.first_name",containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))
                .and().body("items.email",containsInAnyOrder("DLORENTZ","VPATABAL","DAUSTIN","BERNST","AHUNOLD"));

    }}
