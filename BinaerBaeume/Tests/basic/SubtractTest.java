package basic;

import TermAsTreeInheritance.Operator;
import TermAsTreeInheritance.Subtract;
import TermAsTreeInheritance.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractTest {

    @org.junit.jupiter.api.Test
    void getValue() {
        Operator operator = new Subtract(new Value(2.0), new Value(4.0));
        assertEquals(-2.0, operator.getValue());

        operator = new Subtract(new Value(4.0), new Value(2.0));
        assertEquals(2.0, operator.getValue());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Operator operator = new Subtract(new Value(2.0), new Value(4.0));
        assertEquals("-(2.0, 4.0)", operator.toString());

        operator = new Subtract(new Value(4.0), new Value(2.0));
        assertEquals("-(4.0, 2.0)", operator.toString());
    }
}
