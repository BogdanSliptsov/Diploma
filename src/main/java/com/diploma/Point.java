package com.diploma;

/**
 * Created by bohdans on 2/10/2016.
 */
public class Point {
    private Double x;
    private Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Point[")
                .append("x=")
                .append(x)
                .append(", y=")
                .append(y)
                .append("]")
                .toString();
    }
}
