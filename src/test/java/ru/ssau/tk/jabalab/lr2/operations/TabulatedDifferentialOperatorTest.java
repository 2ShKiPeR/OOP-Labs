package ru.ssau.tk.jabalab.lr2.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;

public class TabulatedDifferentialOperatorTest {
    @Test
    void testDeriveWithArrayFactory() {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);

        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0}; // Ожидаемая производная: 2.0

        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction derivativeFunction = operator.derive(function);

        assertEquals(2.0, derivativeFunction.getY(0), 0.0001);
        assertEquals(2.0, derivativeFunction.getY(1), 0.0001);
        assertEquals(2.0, derivativeFunction.getY(2), 0.0001); // Последнее значение
    }

    @Test
    void testDeriveWithLinkedListFactory() {
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);

        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 3.0, 5.0}; // Ожидаемая производная: 2.0

        TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction derivativeFunction = operator.derive(function);

        assertEquals(2.0, derivativeFunction.getY(0), 0.0001);
        assertEquals(2.0, derivativeFunction.getY(1), 0.0001);
        assertEquals(2.0, derivativeFunction.getY(2), 0.0001); // Последнее значение
    }

    @Test
    void testDefaultConstructor() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        assertNotNull(operator.getFactory()); // Убедимся, что фабрика инициализирована
        assertTrue(operator.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    void testSetFactory() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        operator.setFactory(factory);
        assertEquals(factory, operator.getFactory()); // Убедимся, что установленная фабрика возвращается

        LinkedListTabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        operator.setFactory(linkedListFactory);
        assertEquals(linkedListFactory, operator.getFactory()); // Убедимся, что установленная фабрика возвращается
    }
}
