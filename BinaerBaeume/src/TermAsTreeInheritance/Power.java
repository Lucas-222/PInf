package TermAsTreeInheritance;

public class Power extends Operator {

    public Power(Node leftValue, Node rightValue) {
        super(leftValue, rightValue);
    }

    @Override
    public double getValue() {
        return Math.pow(this.leftValue.getValue(), this.rightValue.getValue());
    }

    @Override
    public String toString() {
        return "^(" + this.leftValue + ", " + this.rightValue + ")";
    }
}
