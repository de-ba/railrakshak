/**
 * Created by munde on 08/04/2018.
 */
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Linear {
    public static void main(String[] args) {

        // creating regression object, passing true to have intercept term
        SimpleRegression simpleRegression = new SimpleRegression(true);

        // passing data to the model
        // model will be fitted automatically by the class
        simpleRegression.addData(new double[][] {
                {0, 1},
                {1, 3},
                {2, 5},
                {3, 7},
                {4, 9},
                {5, 11},
                {6, 13},
                {7, 15},
                {8, 17},
                {9, 18}
        });

        // querying for model parameters
        System.out.println("slope = " + simpleRegression.getSlope());
        System.out.println("intercept = " + simpleRegression.getIntercept());

        // trying to run model for unknown data
//        System.out.println("prediction for 1.5 = " + simpleRegression.predict(1.5));

    }

}

