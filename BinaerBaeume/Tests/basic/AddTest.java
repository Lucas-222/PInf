package basic;

import termAsTreeInheritance.Add;
import termAsTreeInheritance.Operator;
import termAsTreeInheritance.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTest {

    @org.junit.jupiter.api.Test
    void getValue() {

        Operator operator = new Add(new Value(2.0), new Value(4.0));
        assertEquals(6.0, operator.getValue());

        operator = new Add(new Value(4.0), new Value(2.0));
        assertEquals(6.0, operator.getValue());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Operator operator = new Add(new Value(2.0), new Value(4.0));
        assertEquals("+(2.0, 4.0)", operator.toString());

        operator = new Add(new Value(4.0), new Value(2.0));
        assertEquals("+(4.0, 2.0)", operator.toString());
    }
}
