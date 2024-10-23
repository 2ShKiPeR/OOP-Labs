package ru.ssau.tk.jabalab.lr2.operations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.ssau.tk.jabalab.lr2.functions.MathFunction;
import ru.ssau.tk.jabalab.lr2.functions.SqrFunction;

public class LeftSteppingDifferentialOperatorTest {
    @Test
    void testLeftSteppingDifferentialOperator() {
        MathFunction function = new SqrFunction();
        LeftSteppingDifferentialOperator left = new LeftSteppingDifferentialOperator(0.5);
        assertEquals(0, Double.compare(7.5, left.derive(function).apply(4)));
        assertEquals(0, left.apply(1));
        assertEquals(0.5, left.getStep());
        left.setStep(0.2);
        assertEquals(0.2, left.getStep());
        assertThrows(IllegalArgumentException.class, () -> new LeftSteppingDifferentialOperator(Double.NaN));
    }
    //(f(x)-f(x-h))/h
}
