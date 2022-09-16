enum Operators {
    ADD {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() + rightNode.getValue();
        }
    },
    SUBTRACT {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() - rightNode.getValue();
        }
    },
    MULTIPLY {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() * rightNode.getValue();
        }
    },
    DIVIDE {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() / rightNode.getValue();
        }
    },
    POWER {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return Math.pow(leftNode.getValue(), rightNode.getValue());
        }
    };

    abstract double getValue(Node leftNode, Node rightNode);
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
}
