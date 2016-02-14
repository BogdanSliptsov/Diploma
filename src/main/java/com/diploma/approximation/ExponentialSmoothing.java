package com.diploma.approximation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by boubdyk on 14.02.2016.
 */
public class ExponentialSmoothing {

    public List<Double> smooth(List<Double> inputPointsY) {
        List<Double> resultList = new ArrayList<Double>();
        resultList.add(inputPointsY.get(0));
        for (int i = 1; i < inputPointsY.size(); i++) {
            resultList.add(resultList.get(i - 1) + bestAlfa(inputPointsY.get(i), resultList.get(i - 1)) * (inputPointsY.get(i) - resultList.get(i - 1)));
        }
        return resultList;
    }

    private Double bestAlfa(double compareWithValue, double previousSmoothingValue) {
        List<Double> alfaList = new ArrayList<Double>();
        List<Double> smoothList = new ArrayList<Double>();
        for (double alfa = 0.0; alfa < 1.0; alfa += 0.01) {
            smoothList.add(compareWithValue - (previousSmoothingValue + alfa * (compareWithValue - previousSmoothingValue)));
            alfaList.add(alfa);
        }
        return alfaList.get(smoothList.indexOf(Collections.min(smoothList)));
    }
}
