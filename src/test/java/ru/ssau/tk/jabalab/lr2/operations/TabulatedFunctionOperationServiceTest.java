package ru.ssau.tk.jabalab.lr2.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.functions.Point;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.factory.ArrayTabulatedFunctionFactory;


import static org.junit.jupiter.api.Assertions.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    void testAsPoints() {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        TabulatedFunction function = factory.create(xValues, yValues);
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        assertEquals(3, points.length); // Убедитесь, что длина массива равна количеству точек
        assertEquals(1.0, points[0].getX());
        assertEquals(2.0, points[0].getY());
        assertEquals(2.0, points[1].getX());
        assertEquals(4.0, points[1].getY());
        assertEquals(3.0, points[2].getX());
        assertEquals(6.0, points[2].getY());
    }
}