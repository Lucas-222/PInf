package termAsTreeInheritance;

public class Variable implements Node {

    public double getValue(double value) {
        return value;
    }

    @Override
    public double getValue() {
        return 0;
    }
}
