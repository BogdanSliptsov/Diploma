package com.diploma.approximation;

import com.diploma.Point;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boubdyk on 29.02.2016.
 */
public class FourierTransform {

    public static List<Point> fourierTransform(List<Point> points) {
        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        double[] f = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            f[i] = points.get(i).getY();
        }

        List<Point> newPointList = new ArrayList<>();
        int counter = 0;
        for (Complex c : transformer.transform(f, TransformType.FORWARD)) {
            newPointList.add(new Point(points.get(counter).getX(), c.getImaginary()));
            counter++;
        }

        return newPointList;
    }
}
