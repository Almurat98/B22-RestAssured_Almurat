package com.cybertek.day05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {




        @Test
        public void simpleTest1(){

            assertThat(5+5, is(10));

            assertThat(5+5, is(10));
            assertThat(5+5,equalTo(10));
            //matchers has 2 overloaded version
            //first that accept actual value
            //second that accept another matchers
            //below examples is method is accepting another matchers equal to make it readable
            assertThat(5+5,is(equalTo(10)));

            assertThat(5+5,not(9));
            assertThat(5+5,is(not(9)));
            assertThat(5+5,is(not(equalTo(9))));

            //number comparison
            //greaterThan()
            //greaterThanOrEqualTo()
            //lessThan()
            //lessThanOrEqualTo()
            assertThat(5+5,is(greaterThan(9)));

        }
    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){

        String text = "B22 is learning Hamcrest";

        //checking for euqality is same as numbers
        assertThat(text,is("B22 is learning Hamcrest"));
        assertThat(text,equalTo("B22 is learning Hamcrest"));
        assertThat(text,is(equalTo("B22 is learning Hamcrest")));

        //check if this text starts with B22
        assertThat(text,startsWith("B22"));
        //now do it in case insensitive manner
        assertThat(text,startsWithIgnoringCase("b22"));
        //endswith
        assertThat(text,endsWith("rest"));

        assertThat(text,containsString("learning"));
        assertThat(text,containsStringIgnoringCase("LEARNING"));

        String str ="  ";

        //check if above str is blank
        assertThat(str,blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());

    }


    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollections(){
        List<Integer> listOfNum= Arrays.asList(1,4,5,7,32,54,66,77,45,23);
        assertThat(listOfNum,hasSize(10));
    }
}
