package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompositeFunctionTest {

    @Test
    public void testCompositeFunction() {
        MathFunction identity = new IdentityFunction();
        MathFunction chaplygin = new ChaplyginFunction();

        // Простая: h(x) = chaplygin(identity(x))
        CompositeFunction composite1 = new CompositeFunction(identity, chaplygin);
        // Проверка: sin(PI/2) = 1
        assertEquals(Math.sin(Math.PI / 2), composite1.apply(Math.PI / 2), 1e-10);

        // Простая: h(x) = identity(chaplygin(x))
        CompositeFunction composite2 = new CompositeFunction(chaplygin, identity);
        // Проверка: identity(sin(PI/2)) = 1
        assertEquals(1.0, composite2.apply(Math.PI / 2), 1e-10);

        // Сложная: h(x) = chaplygin(composite1(x))
        CompositeFunction composite3 = new CompositeFunction(composite1, chaplygin);
        // Проверка: chaplygin(sin(x)) для x=PI/2
        assertEquals(Math.sin(1.0), composite3.apply(Math.PI / 2), 1e-10);

        // сложная: h(x) = composite2(composite1(x))
        CompositeFunction composite4 = new CompositeFunction(composite2, composite1);
        // Проверка: composite1(0) = sin(0) = 0
        assertEquals(0.0, composite4.apply(0.0), 1e-10);
    }
}