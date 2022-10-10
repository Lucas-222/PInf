package TermAsTreeInheritance;

public class Value implements Node {

    private final double value;

    public Value(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
