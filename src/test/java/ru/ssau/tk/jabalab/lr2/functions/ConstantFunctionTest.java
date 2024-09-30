package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantFunctionTest {
    @Test
    public void testApply() {
        ConstantFunction constantFunction1 = new ConstantFunction(0.0);
        assertEquals(0.0, constantFunction1.apply(34.5));
        ConstantFunction constantFunction2 = new ConstantFunction(5.2);
        assertEquals(5.2, constantFunction2.apply(34.5));
        ConstantFunction constantFunction3 = new ConstantFunction(-3.4);
        assertEquals(-3.4, constantFunction3.apply(34.5));
    }
}
