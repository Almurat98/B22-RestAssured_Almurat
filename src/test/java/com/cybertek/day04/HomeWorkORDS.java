package com.cybertek.day04;

import com.cybertek.utilities.ORDSTestBase;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomeWorkORDS extends ORDSTestBase {

    @Test
    public void test1() {
        /**- Given accept type is Json
         - Path param value- US
         - When users sends request to /countries
         - Then status code is 200
         - And Content - Type is Json
         - And country_id is US
         - And Country_name is United States of America
         - And Region_id is
         */

        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"country_id\":\"US\"}").when().get("countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();
        assertEquals("US", jsonPath.getString("items[0].country_id"));
        assertEquals("United States of America", jsonPath.getString("items[0].country_name"));
        assertEquals(2, jsonPath.getInt("items[0].region_id"));
    }


    @Test
    public void test2() {
        /** Given accept type is Json
         - Query param value - q={"department_id":80}
         - When users sends request to /employees
         - Then status code is 200
         - And Content - Type is Json
         - And all job_ids start with 'SA'
         - And all department_ids are 80
         - Count is 25
         */

        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"department_id\":80}").when().get("employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        List<String> jobID = jsonPath.getList("items.job_id");
        for (String each : jobID) {
            assertTrue(each.startsWith("SA"));
        }
        List<Integer> departmentID = jsonPath.getList("items.department_id");
        for (Integer each : departmentID) {
            assertEquals(each, 80);
        }

        assertEquals(25, jsonPath.getInt("count"));


    }


    @Test
    public void test3() {
        /**- Given accept type is Json
         -Query param value q= region_id 3
         - When users sends request to /countries
         - Then status code is 200
         - And all regions_id is 3
         - And count is 6
         - And hasMore is false
         - And Country_name are;
         Australia,China,India,Japan,Malaysia,Singapore

         */

        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"region_id\":3}").when().get("countries");

        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        List<Integer> regionID = jsonPath.getList("items.region_id");
        for (Integer each : regionID) {
            assertEquals(3, each);
        }
        assertEquals(6, jsonPath.getInt("count"));
        assertFalse(jsonPath.getBoolean("hasMore"));
        List<String> expectedCountryName = Arrays.asList("Australia,China,India,Japan,Malaysia,Singapore".split(","));
        List<String> actualCountriesName = jsonPath.getList("items.country_name");
        assertEquals(expectedCountryName, actualCountriesName);

    }


}
