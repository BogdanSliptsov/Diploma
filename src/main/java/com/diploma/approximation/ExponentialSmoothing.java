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
        List<Double> tmpList;
        List<Double> differenceList;
        List<Double> tmpAlfa;
        List<Double> bestAlfa = new ArrayList<Double>();
        resultList.add(inputPointsY.get(0));
        double smoothResult;
        int index;
        for (int i = 1; i <= inputPointsY.size(); i++) {
            differenceList = new ArrayList<Double>();
            tmpList = new ArrayList<Double>();
            tmpAlfa = new ArrayList<Double>();
            for (double alfa = 0.1; alfa < 1.0; alfa += 0.1) {
                smoothResult = resultList.get(i - 1) + alfa * (inputPointsY.get(i - 1) - resultList.get(i - 1));
                tmpList.add(smoothResult);
                differenceList.add(Math.sqrt(Math.pow(inputPointsY.get(i - 1) - smoothResult, 2)));
                tmpAlfa.add(alfa);
            }
            index = differenceList.indexOf(Collections.min(differenceList));
            bestAlfa.add(tmpAlfa.get(index));
            resultList.add(tmpList.get(index));
        }
        resultList.add(resultList.get(resultList.size() - 1) +
                everageAlfa(bestAlfa) *
                        inputPointsY.get(inputPointsY.size() - 1)
                - resultList.get(resultList.size() - 1));
        return resultList;
    }

    private double everageAlfa(List<Double> alfas) {
        int counter = 0;
        double sum = 0.0;
        for (Double d: alfas) {
            sum += d;
            counter ++;
        }
        return sum / counter;
    }
}
