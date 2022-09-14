enum Operators {
    ADD,
    SUBSTRACT,
    MULTIPLY,
    DIVIDE,
    POWER;
}

public class Operator implements Node {
    private Node leftValue;
    private Node rightValue;
    private Operators operator;

    public Operator(Node leftValue, Node rightValue, Operators operator) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.operator = operator;
    }

    @Override
    public double getValue() throws Exception {
        switch (this.operator) {
            case ADD -> {
                return leftValue.getValue() + rightValue.getValue();
            }
            case SUBSTRACT -> {
                return leftValue.getValue() - rightValue.getValue();
            }
            case MULTIPLY -> {
                return leftValue.getValue() * rightValue.getValue();
            }
            case DIVIDE -> {
                return leftValue.getValue() / rightValue.getValue();
            }
            case POWER -> {
                return Math.pow(leftValue.getValue(), rightValue.getValue());
            }
            default -> throw new Exception();
        }
    }
}
