package ru.ssau.tk.jabalab.lr2.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

public class TabulatedFunctionFactoryTest {

    @Test
    void testArrayTabulatedFunctionFactory() {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0};
        double[] yValues = {1.0, 4.0};

        TabulatedFunction function = factory.create(xValues, yValues);
        assertTrue(function instanceof ArrayTabulatedFunction); // Проверяем, что функция - это ArrayTabulatedFunction
    }

    @Test
    void testLinkedListTabulatedFunctionFactory() {
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0};
        double[] yValues = {1.0, 4.0};

        TabulatedFunction function = factory.create(xValues, yValues);
        assertTrue(function instanceof LinkedListTabulatedFunction); // Проверяем, что функция - это LinkedListTabulatedFunction
    }
}
