package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
public abstract class  ORDSTestBase {

    static String myIP=ConfigurationReader.getProperty("ip");



    @BeforeAll
    public static void init(){
        baseURI="http://"+myIP+":1000/ords/hr/";






    }




}
