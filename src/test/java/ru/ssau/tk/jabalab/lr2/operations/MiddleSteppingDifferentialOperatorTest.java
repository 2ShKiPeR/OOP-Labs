package ru.ssau.tk.jabalab.lr2.operations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.ssau.tk.jabalab.lr2.functions.MathFunction;
import ru.ssau.tk.jabalab.lr2.functions.SqrFunction;

public class MiddleSteppingDifferentialOperatorTest {
    @Test
    void test1() {
        MathFunction mathFunction = new SqrFunction();
        MiddleSteppingDifferentialOperator middle = new MiddleSteppingDifferentialOperator(0.5);
        assertEquals(0, Double.compare(6, middle.derive(mathFunction).apply(3)));
        assertEquals(0, middle.apply(1));
    }
    // x^2 (f(x + h) - f(x - h) / 2h)
    // f(3 + 0.5) - f(3 - 0.5) / 1)

}
