package SpecialPoint;

public abstract class SpecialPoint {
    private double xValue;
    private double yValue;

    public SpecialPoint(double xValue, double yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public double getXValue() {
        return xValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    public double getYValue() {
        return yValue;
    }

    @Override
    public String toString() {
        return "x: " + xValue + ", y: " + yValue;
    }
}
