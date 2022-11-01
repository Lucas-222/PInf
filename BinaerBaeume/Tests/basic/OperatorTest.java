package basic;

import termAsTreeInheritance.Add;
import termAsTreeInheritance.Operator;
import termAsTreeInheritance.Power;
import termAsTreeInheritance.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperatorTest {

    @org.junit.jupiter.api.Test
    void getValue() {
        Operator operator = new Power(new Add(new Value(2.0), new Value(3.0)), new Value(2.0));
        assertEquals(25.0, operator.getValue());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Operator operator = new Power(new Add(new Value(2.0), new Value(3.0)), new Value(2.0));
        assertEquals("^(+(2.0, 3.0), 2.0)", operator.toString());
    }
}
