package com.diploma.approximation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boubdyk on 11.02.2016.
 */
public class Test {

    public static void testPolynomialApproximation(List<Point> points) {
        PolynomialApproximation testCase = new PolynomialApproximation(points);

//        System.out.println("\n\n" + testCase.approximateFunction(0.0, 6) + "\n");
//
//        for (Double d: testCase.standardDeviation(10)) {
//            System.out.println(d);
//        }

        //The most accuracy function
//        System.out.println("\nFunction=" + testCase.solveLinearEquation(testCase.bestExponent(testCase.standardDeviation(10))));

        System.out.println("\n\n" + testCase.approximateFunction(4.0, 3));

//        System.out.println(testCase.solveLinearEquation(6));
    }

    public static void testEponentialSmoothing(List<Point> points) {
        PolynomialApproximation testCase = new PolynomialApproximation(points);
        ExponentialSmoothing exponentialSmoothing = new ExponentialSmoothing();
        List<Double> inputListForSmoothing = new ArrayList<Double>();
        for (int i = 0; i < points.size(); i++) {
            inputListForSmoothing.add(points.get(i).getY());
        }
        for (Double d: exponentialSmoothing.smooth(inputListForSmoothing)) {
            System.out.println(d);
        }
    }

    public static void testFourierSeries(List<Point> points) {
        for (Double d: new FourierSeries(points).fourierSeriesApproximation()) {
            System.out.println(d);
        }
    }


    public static void main(String[] args) {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(-3.0, -4.0));
        points.add(new Point(-1.0, -0.8));
        points.add(new Point(0.0, 1.6));
        points.add(new Point(1.0, 2.3));
        points.add(new Point(3.0, 1.5));

//        testPolynomialApproximation(points);
//        testEponentialSmoothing(points);
        testFourierSeries(points);

    }

}
