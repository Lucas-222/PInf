package basic;

import TermAsTreeInheritance.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueTest {

    @org.junit.jupiter.api.Test
    void getValue() {
        Value value = new Value(2.0);
        assertEquals(2.0, value.getValue());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Value value = new Value(2.0);
        assertEquals("2.0", value.toString());
    }
}
