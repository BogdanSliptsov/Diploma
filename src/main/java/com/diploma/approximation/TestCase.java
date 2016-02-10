package com.diploma.approximation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by boubdyk on 09.02.2016.
 */
public class TestCase {

    List <Point> points;

    public TestCase(List<Point> points) {
        this.points = points;
    }

    //Number of coefficients
    private final int M = 1;

    //Number of points
    private final int N = 5;

    private Double sumRightPart(List<Point> points, int exponentForX) {
        Double result = 0.0;
        for (Point point: points) {
            result += point.getX() * Math.pow(point.getY(), exponentForX);
        }
        return result;
    }

    private Double sumLeftPart(List<Point> points, int exponentForX) {
        Double result = 0.0;
        Double sum;
        for (int j = 0; j <= M; j++) {
            sum = 0.0;
            for (int i = 0; i < points.size(); i++) {
                sum += Math.pow(points.get(i).getX(), j + exponentForX);
            }
            result += sum;
        }
        return result;
    }

    public List<Double> calculateCoefficients() {
        List<Double> resultList = new ArrayList<Double>();
        for (int i = 0; i <= M; i++) {
            resultList.add(sumLeftPart(points, M) / sumRightPart(points, M));
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(0.0, 2.1));
        points.add(new Point(1.0, 2.4));
        points.add(new Point(2.0, 2.6));
        points.add(new Point(4.0, 2.8));
        points.add(new Point(5.0, 3.0));

        TestCase testCase = new TestCase(points);

        for (Double d: testCase.calculateCoefficients()) {
            System.out.println(d);
        }
    }

}
