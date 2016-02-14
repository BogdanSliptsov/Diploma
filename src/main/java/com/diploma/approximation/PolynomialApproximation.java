package com.diploma.approximation;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bohdans on 2/10/2016.
 */
public class PolynomialApproximation {
    private List<Point> points;
    private List<Double> sumCoefficients;

    public PolynomialApproximation(List<Point> points) {
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
    private List<Double> fillSumCoefficientsLP(final int NUMBER_OF_EQUATIONS) {
        List<Double> resultList = new ArrayList<Double>();
        for (int i = 0; i < 2 * NUMBER_OF_EQUATIONS - 1; i++) {
            resultList.add(calculateSumCoefficientsLP(points, i, points.size()));
        }
        return resultList;
    }

    //Fill coefficients sum of the right part
    private List<Double> fillSumCoefficientsRP(final int NUMBER_OF_EQUATIONS) {
        List<Double> resultList = new ArrayList<Double>();
        for (int i = 0; i < NUMBER_OF_EQUATIONS; i++) {
            resultList.add(calculateSumCoefficientsRP(points, i, points.size()));
        }
        return resultList;
    }

    //3 in loop is number of equations
    private double[][] fillLeftPartMatrix(final int NUMBER_OF_EQUATIONS) {
        double[][] result = new double[NUMBER_OF_EQUATIONS][NUMBER_OF_EQUATIONS];
        for (int i = 0; i < NUMBER_OF_EQUATIONS; i++) {
            for (int j = 0; j < NUMBER_OF_EQUATIONS; j++) {
                result[i][j] = fillSumCoefficientsLP(NUMBER_OF_EQUATIONS).get(i + j);
            }
        }
        return result;
    }

    //Solving linear equations in matrix form: A*X=B
    public DoubleMatrix solveLinearEquation(final int NUMBER_OF_EQUATIONS) {
        DoubleMatrix B = new DoubleMatrix(fillSumCoefficientsRP(NUMBER_OF_EQUATIONS));
        DoubleMatrix A = new DoubleMatrix(fillLeftPartMatrix(NUMBER_OF_EQUATIONS));

        return Solve.solve(A, B);
    }

    //Returns value of approximated function
    public double approximateFunction(double x, final int NUMBER_OF_EQUATIONS) {
        DoubleMatrix X = solveLinearEquation(NUMBER_OF_EQUATIONS);
        double result = 0.0;
        for (int i = 0; i < NUMBER_OF_EQUATIONS; i++) {
            result += X.get(i) * Math.pow(x, i);
        }
        return result;
    }

    //Standard deviation for different exponents < maxExponent (loop condition)
    public List<Double> standardDeviation(int maxExponent) {
        List<Double> result = new ArrayList<Double>();
        double standardDeviation;
        for (int i = 1; i <= maxExponent; i++) {
            standardDeviation = 0.0;
            for (int j = 0; j < points.size(); j++) {
                standardDeviation += Math.pow(approximateFunction(points.get(j).getX(), i) - points.get(j).getY(), 2);
            }
            result.add(Math.sqrt((1.0 / ((double)points.size() + 1.0)) * standardDeviation));
        }
        return result;
    }

    //Returns exponent that gives the most accuracy value of approximated function
    public int bestExponent(List<Double> standardDeviationList) {
        return standardDeviationList.indexOf(Collections.min(standardDeviationList));
    }

}
