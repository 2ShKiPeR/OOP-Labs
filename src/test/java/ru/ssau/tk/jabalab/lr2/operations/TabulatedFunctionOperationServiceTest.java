package ru.ssau.tk.jabalab.lr2.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.functions.Point;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.exceptions.InconsistentFunctionsException;

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
        assertEquals(1.0, points[0].x);
        assertEquals(2.0, points[0].y);
        assertEquals(2.0, points[1].x);
        assertEquals(4.0, points[1].y);
        assertEquals(3.0, points[2].x);
        assertEquals(6.0, points[2].y);
    }

    @Test
    void test6() {
        TabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        assertThrows(InconsistentFunctionsException.class, () -> service.addition(function1, function2));
    }

    @Test
    void test7() {
        TabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunction tabulated = service.subtraction(function1, function2);

        double[] expectedX = {1, 2, 3, 4, 5};
        double[] expectedY = {0, 0, 0, 0, 0};

        for (int i = 0; i < tabulated.getCount(); i++) {
            assertEquals(expectedX[i], tabulated.getX(i), 0.001);
            assertEquals(expectedY[i], tabulated.getY(i), 0.001);
        }
    }

    @Test
    void test8() {
        TabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunction tabulated = service.subtraction(function1, function2);
        double[] expectedX = {1, 2, 3, 4, 5};
        double[] expectedY = {0, 0, 0, 0, 0};

        for (int i = 0; i < tabulated.getCount(); i++) {
            assertEquals(expectedX[i], tabulated.getX(i), 0.001);
            assertEquals(expectedY[i], tabulated.getY(i), 0.001);
        }
    }

    @Test
    void test9() {
        TabulatedFunction function1 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunction tabulated = service.subtraction(function1, function2);
        double[] expectedX = {1, 2, 3, 4, 5};
        double[] expectedY = {0, 0, 0, 0, 0};

        for (int i = 0; i < tabulated.getCount(); i++) {
            assertEquals(expectedX[i], tabulated.getX(i), 0.001);
            assertEquals(expectedY[i], tabulated.getY(i), 0.001);
        }
    }

    @Test
    void test10() {
        TabulatedFunction function1 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunction tabulated = service.subtraction(function1, function2);
        double[] expectedX = {1, 2, 3, 4, 5};
        double[] expectedY = {0, 0, 0, 0, 0};

        for (int i = 0; i < tabulated.getCount(); i++) {
            assertEquals(expectedX[i], tabulated.getX(i), 0.001);
            assertEquals(expectedY[i], tabulated.getY(i), 0.001);
        }
    }

    @Test
    void test11() {
        TabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{0, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        assertThrows(InconsistentFunctionsException.class, () -> service.subtraction(function1, function2));
    }

    @Test
    void test12() {
        TabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunction newTabFunc = service.multiplication(function1,function2);
        double[] expectedX = {1, 2, 3, 4, 5};
        double[] expectedY = {1, 4, 9, 16, 25};

        for (int i = 0; i < newTabFunc.getCount(); i++) {
            assertEquals(expectedX[i], newTabFunc.getX(i), 0.001);
            assertEquals(expectedY[i], newTabFunc.getY(i), 0.001);
        }
    }

    @Test
    void test13() {
        TabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunction newTabFunc = service.division(function1,function2);
        double[] expectedX = {1, 2, 3, 4, 5};
        double[] expectedY = {1, 1, 1, 1, 1};

        for (int i = 0; i < newTabFunc.getCount(); i++) {
            assertEquals(expectedX[i], newTabFunc.getX(i), 0.001);
            assertEquals(expectedY[i], newTabFunc.getY(i), 0.001);
        }
    }
    @Test
    void test14() {
        TabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 2, 9, 5});
        TabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunction newTabFunc = service.division(function1,function2);
        double[] expectedX = {1, 2, 3, 4, 5};
        double[] expectedY = {1, 1, 0.6666666666666666, 2.25, 1};

        for (int i = 0; i < newTabFunc.getCount(); i++) {
            assertEquals(expectedX[i], newTabFunc.getX(i), 0.001);
            assertEquals(expectedY[i], newTabFunc.getY(i), 0.001);
        }
    }
    @Test
    void test15() {
        TabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
        TabulatedFunction function2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        assertThrows(InconsistentFunctionsException.class, () -> service.addition(function1, function2));
        TabulatedFunctionFactory func = service.getFactory();
        service.setFactory(func);
    }
}
