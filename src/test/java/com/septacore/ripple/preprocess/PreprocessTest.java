/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.septacore.ripple.preprocess;

import com.septacore.ripple.preprocess.types.PPType;
import com.septacore.ripple.preprocess.types.PPTypeBoolean;
import com.septacore.ripple.preprocess.types.PPTypeDouble;
import com.septacore.ripple.preprocess.types.PPTypeString;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test preprocesser functionalities, mainly the sanity of the parse tuples
 * function and then of the function applications
 *
 * @author andrei
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ripple-context.xml" })
public class PreprocessTest extends TestCase{

    private PPVariableTable varTable;

    @Autowired
    private PPMain ppMain;

    public PreprocessTest() {
        /// Initialize variables (i.e. wireshark attributes)
        varTable = new PPVariableTable();
        varTable.create("http.user_agent", new PPTypeString());
        varTable.create("data-text-lines", new PPTypeString());
    }


  /*  public void testParseTuples() throws PPError {

        // Initialize tuples 
        PPBase[] test = PPMain.parseTuples(
                "sd(potato(5,6));9;get(urldecode(getquerystring(http.user_agent)),\"potato\");get(urldecode(getquerystring(http.user_agent)),\"orange\");"
                + "jscore(getelementsbytagname(parsehtml(data-text-lines),\"script\"));"
                + "jscore(getelementsbytagname(parsehtml(data-text-lines),\"span\"));", new PPType[]{PPType.DoubleType, PPType.UTF8String, PPType.ASCIIString, PPType.ASCIIString, PPType.DoubleType, PPType.DoubleType}, varTable);


        // First set of data going through the preprocessor;

        varTable.set("http.user_agent", (String) "www.google.com?potato=noooo2&orange=yes");
        varTable.set("data-text-lines", (String) "<body><script>hello</script><script>wag1</script><script>bye</script><span>hi</span></body>");

        PPBase ascii1 = test[2];
        PPBase number1 = test[4];

        assertEquals(ascii1.toString(0), "ASCIIString : noooo2");
        assertEquals(number1.toString(0), "DoubleType : 0");



        // Second set of data going through, check if the preprocessing is still
        //done correctly:
        varTable.set("http.user_agent", (String) "www.google.com?potato=yeeeess2&orange=no");
        varTable.set("data-text-lines", (String) "<body><script>bye</script><span>hi</span><span>moo</span></body>");

        assertEquals(ascii1.toString(1), "ASCIIString : yeeeess2");
        assertEquals(number1.toString(1), "DoubleType : 0");


    }*/

    /**
     * Add to this function when testing new type castings done by the
     * preprocessor
     *
     * @throws PPError
     */

    @Test
    public void testPreprocessorApplications() throws PPError {
        varTable.create("val1", new PPTypeString());
        PPBase[] test = ppMain.parseTuples(
                "val1;len(val1);0;1;", new PPType[]{new PPTypeString(), new PPTypeDouble(), new PPTypeBoolean(),
                    new PPTypeBoolean()}, varTable);

        varTable.set("val1", "value1");

        assertEquals(test[0].getValue(0), "value1");
        assertEquals(test[1].getValue(0), 6.0);
        assertEquals(test[2].getValue(0), false);
        assertEquals(test[3].getValue(0), true);

    }

    /**
     * Add newly created preprocessor functions for testing here.
     *
     * @throws PPError
     */
    @Test
    public void testPreprocessorFunctions() throws PPError {
        varTable.create("val1", new PPTypeString());
        PPBase[] test = ppMain.parseTuples(
                "val1;len(val1);log(100,10);skewlen(val1);skewloglen(val1);", new PPType[]{new PPTypeString(), new PPTypeDouble(), new PPTypeDouble(),
                    new PPTypeDouble(), new PPTypeDouble()}, varTable);

        varTable.set("val1", "value1");

        assertEquals(test[0].getValue(0), "value1");
        assertEquals(test[1].getValue(0), 6.0);
        assertEquals(test[2].getValue(0), 2.0);
        assertEquals(test[3].getValue(0), 36.0);
        assert ((Double) test[4].getValue(0) < 1.8 && (Double) test[4].getValue(0) > 1.7);


    }
    @Before
    public  void setUp() throws Exception {
    }

    @After
    public  void tearDown() throws Exception {
    }
}
