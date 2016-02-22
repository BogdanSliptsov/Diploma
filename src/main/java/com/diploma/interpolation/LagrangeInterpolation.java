package com.diploma.interpolation;

import com.diploma.Point;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;

import java.util.List;

/**
 * Created by boubdyk on 22.02.2016.
 */
public class LagrangeInterpolation {
    private double[] xValues;
    private double[] yValues;

    public LagrangeInterpolation(List<Point> points) {
        super();
        xValues = new double[points.size()];
        yValues = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            xValues[i] = points.get(i).getX();
            yValues[i] = points.get(i).getY();
        }
    }

    public Double calculate(double x) {
        return PolynomialFunctionLagrangeForm.evaluate(xValues, yValues, x);
    }
}
