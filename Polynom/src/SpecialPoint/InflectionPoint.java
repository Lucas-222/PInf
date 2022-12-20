package SpecialPoint;

public class InflectionPoint extends SpecialPoint {
    private final boolean isRightLeftTurning;

    public InflectionPoint(double xValue, double yValue, boolean isRightLeftTurning) {
        super(xValue, yValue);
        this.isRightLeftTurning = isRightLeftTurning;
    }

    public boolean isRightLeftTurning() {
        return isRightLeftTurning;
    }

    @Override
    public String toString() {
        return super.toString() + ", is right left turning: " + isRightLeftTurning;
    }

}
