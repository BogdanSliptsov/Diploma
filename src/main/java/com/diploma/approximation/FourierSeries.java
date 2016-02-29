package com.diploma.approximation;

import com.diploma.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by boubdyk on 16.02.2016.
 */
public class FourierSeries {

    private List<Double> yValues;
    private List<Double> xValues;
    private List<Point> points;
    private final int N;

    public FourierSeries(List<Point> points) {
        List<Double> yValues = new ArrayList<Double>();
        List<Double> xValues = new ArrayList<Double>();
        for (Point p: points) {
            yValues.add(p.getY());
            xValues.add(p.getX());
        }
        this.points = points;
        this.yValues = yValues;
        this.xValues = xValues;
        this.N = points.size();
           }

    private double countSum() {
        double sum = 0.0;
        for (Double d: yValues) {
            sum += d;
        }
        return sum;
    }

    private Double countA0() {
        return 1.0 / yValues.size() * countSum();
    }

    private Double countAk(int k, Double t) {
        return 2.0 / N * countSum() * Math.cos(k * t);
    }

    private Double countBk(int k, Double t) {
        return 2.0 / N * countSum() * Math.sin(k * t);
    }


    // 4 in loop is max number of garmonics
    public Double calculation(Double t, Double y) {
        List<Double> sumResults = new ArrayList<Double>();
        List<Double> differenceList = new ArrayList<Double>();
        for (int i = 0; i < 10; i++) {
            double tmpResult = 0.0;
            for (int j = 1; j <= i + 1; j++) {
                tmpResult += countAk(j, t) * Math.cos(j * t) + countBk(j, t) * Math.sin(j * t);
            }
            sumResults.add(countA0() + tmpResult);
            differenceList.add(Math.sqrt(Math.pow(sumResults.get(i) - y, 2)));
        }
        return sumResults.get(differenceList.indexOf(Collections.min(differenceList)));
    }

    public List<Double> fourierSeriesApproximation(Double pointToApproximate) {
        List<Double> resultList = new ArrayList<Double>();
        for (int i = 0; i < N; i++) {
            resultList.add(calculation(xValues.get(i), yValues.get(i)));
        }
//        resultList.add(calculation(pointToApproximate));
        return resultList;
    }

}
