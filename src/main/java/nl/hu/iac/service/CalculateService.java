package nl.hu.iac.service;

import net.sourceforge.jdistlib.Normal;
import net.sourceforge.jdistlib.disttest.NormalityTest;
import net.sourceforge.jdistlib.util.Utilities;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

/**
 * Created by njvan on 22-Jun-17.
 */
public class CalculateService {
    public static double calculateMean(JSONArray array) throws JSONException {
        double sum = calculateSum(array);
        return sum / array.length();
    }

    public static double calculateStandardDeviation(JSONArray array) throws JSONException {
        double arrayMean = calculateMean(array);
        double sum = 0;
        for(int i = 0; i < array.length(); i++){
            sum += Math.pow(Math.abs(arrayMean - array.getInt(i)),2);
        }
        return Math.sqrt(sum / array.length());
    }

    public static boolean checkIfNormalDistribution(JSONArray array) throws JSONException {
        double[] numberArray = new double[array.length()];
        for(int i = 0; i < array.length(); i++){
            numberArray[i] = array.getInt(i);
        }
        Utilities.sort(numberArray);
        return NormalityTest.anderson_darling_statistic(numberArray) < 1;
    }

    private static int calculateSum(JSONArray array) throws JSONException {
        int sum = 0;
        for(int i = 0; i < array.length(); i++){
            sum += array.getInt(i);
        }
        return sum;
    }
}
