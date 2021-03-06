package com.diploma;

import com.diploma.Point;
import com.diploma.approximation.ExponentialSmoothing;
import com.diploma.approximation.FourierSeries;
import com.diploma.approximation.PolynomialApproximation;
import com.diploma.interpolation.LagrangeInterpolation;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boubdyk on 11.02.2016.
 */
public class TestMaths {

    public static void testPolynomialApproximation(List<Point> points) {
        PolynomialApproximation testCase = new PolynomialApproximation(points);

//        System.out.println("\n\n" + testCase.approximateFunction(0.0, 6) + "\n");
//
//        for (Double d: testCase.standardDeviation(10)) {
//            System.out.println(d);
//        }

        //The most accuracy function
//        System.out.println("\nFunction=" + testCase.solveLinearEquation(testCase.bestExponent(testCase.standardDeviation(10))));

        System.out.println("\n\n" + testCase.approximateFunction(5.0, testCase.bestExponent(testCase.standardDeviation(10))));

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
        FourierSeries fourierSeries = new FourierSeries(points);

        for (Double d: fourierSeries.fourierSeriesApproximation(4.0)) {
            System.out.println(d);
        }
        System.out.println();
        for (Double d: fourierSeries.fourierSeriesApproximation(5.0)) {
            System.out.println(d);
        }
        System.out.println();
        for (Double d: fourierSeries.fourierSeriesApproximation(6.0)) {
            System.out.println(d);
        }
    }

    public static void testLagrangeInterpolation(List<Point> points) {
        LagrangeInterpolation testCase = new LagrangeInterpolation(points);

        System.out.println(testCase.calculate(-1.5));
    }


    public static void main(String[] args) {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(-3.0, -4.0));
        points.add(new Point(-1.0, -0.8));
        points.add(new Point(0.0, 1.6));
        points.add(new Point(1.0, 2.3));
//        points.add(new Point(3.0, 1.5));

//        testPolynomialApproximation(points);
//        testEponentialSmoothing(points);
        testFourierSeries(points);
//        testLagrangeInterpolation(points);
//        fourierTransform(points);
    }

    public static void fourierTransform(List<Point> points) {
        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        double[] f = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            f[i] = points.get(i).getY();
        }

        for (Complex c : transformer.transform(f, TransformType.FORWARD)) {
            System.out.println(c.getImaginary());
        }

    }

}
