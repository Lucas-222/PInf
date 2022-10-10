package TermAsTreeInheritance;

public abstract class Operator implements Node {

    protected final Node leftValue;
    protected final Node rightValue;

    Operator(Node leftValue, Node rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }
}
