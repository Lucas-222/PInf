enum Operators {
    ADD,
    SUBSTRACT,
    MULTIPLY,
    DIVIDE,
    POWER;
}

public class Operator implements Node {
    private Node leftNode;
    private Node rightNode;
    private Operators operator;

    public Operator(Node leftValue, Node rightValue, Operators operator) {
        this.leftNode = leftValue;
        this.rightNode = rightValue;
        this.operator = operator;
    }

    @Override
    public double getValue() throws Exception {
        switch (this.operator) {
            case ADD -> {
                return leftNode.getValue() + rightNode.getValue();
            }
            case SUBSTRACT -> {
                return leftNode.getValue() - rightNode.getValue();
            }
            case MULTIPLY -> {
                return leftNode.getValue() * rightNode.getValue();
            }
            case DIVIDE -> {
                return leftNode.getValue() / rightNode.getValue();
            }
            case POWER -> {
                return Math.pow(leftNode.getValue(), rightNode.getValue());
            }
            default -> throw new Exception();
        }
    }
}
