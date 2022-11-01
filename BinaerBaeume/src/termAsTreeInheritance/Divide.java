package termAsTreeInheritance;

public class Divide extends Operator {

    public Divide(Node leftValue, Node rightValue) {
        super(leftValue, rightValue);
    }

    @Override
    public double getValue() {
        return this.leftValue.getValue() / this.rightValue.getValue();
    }

    @Override
    public String toString() {
        return "/(" + this.leftValue + ", " + this.rightValue + ")";
    }
}
