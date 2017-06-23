package nl.hu.iac.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import us.monoid.json.JSONException;
import us.monoid.web.Resty;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static us.monoid.web.Resty.data;
import static us.monoid.web.Resty.form;

/**
 * Created by njvan on 22-Jun-17.
 */
public class ServiceTest {
    private static Resty resty;
    private double[] array1;
    private double[] array2;
    private double[] array3;
    private String input1;
    private String input2;
    private String input3;

    @BeforeClass
    public static void beforeClass(){
        resty = new Resty();
    }

    @Before
    public void before(){
        array1 = new double[]{24,2,5,7,9,10,12};
        array2 = new double[]{4,2,3,4,5,6,5,7,5};
        array3 = new double[]{2,3,2,3,2,3,2,4,2};
        input1 = Arrays.toString(array1);
        input2 = Arrays.toString(array2);
        input3 = Arrays.toString(array3);
    }

    @Test
    public void testCheckNormalDistribution() throws IOException, JSONException {
        String result1 = resty.json("http://localhost:8080/calculateService/rest/checkNormalDistribution", form(input1)).object().get("result").toString();
        String result2 = resty.json("http://localhost:8080/calculateService/rest/checkNormalDistribution", form(input2)).object().get("result").toString();
        String result3 = resty.json("http://localhost:8080/calculateService/rest/checkNormalDistribution", form(input3)).object().get("result").toString();
        assertEquals(result1,"true");
        assertEquals(result2,"true");
        assertEquals(result3,"false");
    }

    @Test
    public void testCalculateMean() throws Exception {
        String result1 = resty.json("http://localhost:8080/calculateService/rest/calculateMean", form(input1)).object().get("result").toString();
        String result2 = resty.json("http://localhost:8080/calculateService/rest/calculateMean", form(input2)).object().get("result").toString();
        String result3 = resty.json("http://localhost:8080/calculateService/rest/calculateMean", form(input3)).object().get("result").toString();
        double double1 = Math.round(Double.parseDouble(result1) * 100.0) / 100.0;
        double double2 = Math.round(Double.parseDouble(result2) * 100.0) / 100.0;
        double double3 = Math.round(Double.parseDouble(result3) * 100.0) / 100.0;
        assertEquals(double1, 9.86, 0);
        assertEquals(double2, 4.56, 0);
        assertEquals(double3, 2.56, 0);
    }

    @Test
    public void testCalculateStandardDeviation() throws IOException, JSONException {
        String result1 = resty.json("http://localhost:8080/calculateService/rest/calculateStandardDeviation", form(input1)).object().get("result").toString();
        String result2 = resty.json("http://localhost:8080/calculateService/rest/calculateStandardDeviation", form(input2)).object().get("result").toString();
        String result3 = resty.json("http://localhost:8080/calculateService/rest/calculateStandardDeviation", form(input3)).object().get("result").toString();
        double double1 = Math.round(Double.parseDouble(result1) * 100.0) / 100.0;
        double double2 = Math.round(Double.parseDouble(result2) * 100.0) / 100.0;
        double double3 = Math.round(Double.parseDouble(result3) * 100.0) / 100.0;
        assertEquals(double1, 6.53, 0);
        assertEquals(double2, 1.42, 0);
        assertEquals(double3, 0.68, 0);
    }
}
