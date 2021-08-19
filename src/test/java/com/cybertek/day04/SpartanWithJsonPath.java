package com.cybertek.day04;

import com.cybertek.utilities.ORDSTestBase;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJsonPath extends SpartanTestBase {



    @DisplayName("GET one spartan with JsonPath")
    @Test
    public void test1(){
        Response response= given().accept(ContentType.JSON).and().pathParam("id",10).when().get("/api/spartans/{id}");

        JsonPath jsonPath=response.jsonPath();

        int id=  jsonPath.getInt("id");
        String name =jsonPath.getString("name");
        String gender=jsonPath.getString("gender");
        long phoneNum=jsonPath.getLong("phone");


        System.out.println(id);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phoneNum);

    }



}
