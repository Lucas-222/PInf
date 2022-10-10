package basic;

import TermAsTreeInheritance.Divide;
import TermAsTreeInheritance.Operator;
import TermAsTreeInheritance.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideTest {

    @org.junit.jupiter.api.Test
    void getValue() {

        Operator operator = new Divide(new Value(2.0), new Value(4.0));
        assertEquals(0.5, operator.getValue());

        operator = new Divide(new Value(4.0), new Value(2.0));
        assertEquals(2.0, operator.getValue());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Operator operator = new Divide(new Value(2.0), new Value(4.0));
        assertEquals("/(2.0, 4.0)", operator.toString());

        operator = new Divide(new Value(4.0), new Value(2.0));
        assertEquals("/(4.0, 2.0)", operator.toString());
    }
}
