enum Operators {
    ADD {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() + rightNode.getValue();
        }
        @Override
        public String toString() {
            return "+";
        }
    },
    SUBTRACT {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() - rightNode.getValue();
        }
        @Override
        public String toString() {
            return "-";
        }
    },
    MULTIPLY {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() * rightNode.getValue();
        }
        @Override
        public String toString() {
            return "*";
        }
    },
    DIVIDE {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() / rightNode.getValue();
        }
        @Override
        public String toString() {
            return "/";
        }
    },
    POWER {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return Math.pow(leftNode.getValue(), rightNode.getValue());
        }
        @Override
        public String toString() {
            return "^";
        }
    };

    abstract double getValue(Node leftNode, Node rightNode);
    public abstract String toString();
}

public class Operator implements Node {
    private Node leftNode;
    private Node rightNode;
    private Operators operator;

    Operator(Node leftNode, Node rightNode, Operators operator) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.operator = operator;
    }

    Operator(double leftValue, Node rightNode, Operators operator) {
        this(new Value(leftValue), rightNode, operator);
    }

    Operator(double leftValue, double rightValue, Operators operator) {
        this(new Value(leftValue), new Value(rightValue), operator);
    }

    Operator(Node leftNode, double rightValue, Operators operator) {
        this(leftNode, new Value(rightValue), operator);
    }

    @Override
    public double getValue() {
        return this.operator.getValue(this.leftNode, this.rightNode);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(this.leftNode.toString());
        builder.append(",");
        builder.append(this.rightNode.toString());
        builder.append(")");
        builder.append(this.operator.toString());
        return builder.toString();
    }
}
