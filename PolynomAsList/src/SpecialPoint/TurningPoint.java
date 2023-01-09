package SpecialPoint;

public class TurningPoint extends SpecialPoint {
    public boolean isGlobal;

    public TurningPoint(double xValue, double yValue, boolean isGlobal) {
        super(xValue, yValue);
        this.isGlobal = isGlobal;
    }

    @Override
    public String toString() {
        return super.toString() + ", is global: " + isGlobal;
    }

}
