package com.diploma;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by bohdans on 2/10/2016.
 */
public class TestCase {
    private List<Point> points;
    private List<Double> sumCoefficients;

    public TestCase(List<Point> points) {
        this.points = points;
    }

    // Calculate coefficients sum of the left part
    private Double calculateSumCoefficientsLP(List<Point> inputPoints, int exponent, int numberOfPoints) {
        Double result = 0.0;
        for (int i = 0; i < numberOfPoints; i++) {
            result += Math.pow(inputPoints.get(i).getX(), exponent);
        }
        return result;
    }

    //Calculate coefficients sum of the right part
    private Double calculateSumCoefficientsRP(List<Point> inputPoints, int exponentForX, int numberOfPoints) {
        Double result = 0.0;
        for (int i = 0; i < numberOfPoints; i++) {
            result += inputPoints.get(i).getY() * Math.pow(inputPoints.get(i).getX(), exponentForX);
        }
        return result;
    }


    //Fill coefficients sum of the left part
    private List<Double> fillSumCoefficientsLP() {
        List<Double> resultList = new ArrayList<Double>();
        for (int i = 0; i < points.size(); i++) {
            resultList.add(calculateSumCoefficientsLP(points, i, points.size()));
        }
        return resultList;
    }

    //Fill coefficients sum of the right part
    private List<Double> fillSumCoefficientsRP() {
        List<Double> resultList = new ArrayList<Double>();
        for (int i = 0; i <= (int) Math.sqrt(fillSumCoefficientsLP().size()); i++) {
            resultList.add(calculateSumCoefficientsRP(points, i, points.size()));
        }
        return resultList;
    }

    //3 in loop is number of equations
    private double[][] fillLeftPartMatrix() {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = fillSumCoefficientsLP().get(i + j);
            }
        }
        return result;
    }

    //Solving linear equations in matrix form: A*X=B
    public DoubleMatrix solveLinearEquation() {
        DoubleMatrix B = new DoubleMatrix(fillSumCoefficientsRP());
        DoubleMatrix A = new DoubleMatrix(fillLeftPartMatrix());

        return Solve.solve(A, B);
    }

    public double approximateFunction(double x) {
        DoubleMatrix X = solveLinearEquation();
        return X.get(0) + X.get(1) * x + X.get(2) * Math.pow(x, 2);
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(-3.0, -4.0));
        points.add(new Point(-1.0, -0.8));
        points.add(new Point(0.0, 1.6));
        points.add(new Point(1.0, 2.3));
        points.add(new Point(3.0, 1.5));

        TestCase testCase = new TestCase(points);

        System.out.println(testCase.solveLinearEquation());

        System.out.println("\n\n" + testCase.approximateFunction(3.0));

    }

}
