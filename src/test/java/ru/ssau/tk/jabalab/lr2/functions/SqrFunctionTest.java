package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqrFunctionTest {
    @Test
    public void testSqrFunction() {
        SqrFunction sqrFunction = new SqrFunction();

        assertEquals(4, sqrFunction.apply(2));
        assertEquals(1, sqrFunction.apply(-1));
        assertEquals(144, sqrFunction.apply(12));
        assertEquals(36, sqrFunction.apply(-6));
        assertEquals(0, sqrFunction.apply(0));
    }
}
