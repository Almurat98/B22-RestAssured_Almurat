package com.cybertek.day03;

import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiTestWithParam extends ORDSTestBase {




    @Test
    public void test1(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));

    }


    @Test
    public void test2(){
        Response response=given().and().queryParam("q","{\"job_id\":\"IT_PROG\"}").when().get("employees");

        response.prettyPrint();
    }


}
