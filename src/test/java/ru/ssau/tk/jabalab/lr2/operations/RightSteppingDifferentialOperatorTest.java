package ru.ssau.tk.jabalab.lr2.operations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.ssau.tk.jabalab.lr2.functions.MathFunction;
import ru.ssau.tk.jabalab.lr2.functions.SqrFunction;

public class RightSteppingDifferentialOperatorTest {
    @Test
    void testRightSteppingDifferentialOperator() {
        MathFunction mathFunction = new SqrFunction();
        RightSteppingDifferentialOperator right = new RightSteppingDifferentialOperator(0.5);
        assertEquals(0, Double.compare(8.5, right.derive(mathFunction).apply(4)));
        assertEquals(0, right.apply(1));
    }

    //(f(x+h)-f(x))/h
}
