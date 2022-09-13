public enum Operator implements Node {
    ADD,
    SUBSTRACT,
    MULTIPLY,
    DIVIDE,
    POWER;
    private Node leftValue;
    private Node rightValue;
    Operator() {}

    Operator(Node leftValue, Node rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    @Override
    public double getValue() throws Exception {
        switch (this) {
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
