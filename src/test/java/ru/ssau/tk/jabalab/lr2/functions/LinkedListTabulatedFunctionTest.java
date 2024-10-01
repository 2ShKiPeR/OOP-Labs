package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    @Test
    void testConstructorWithArrays() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(3, function.getCount());
        assertEquals(1.0, function.leftBound());
        assertEquals(3.0, function.rightBound());
    }

    @Test
    void testConstructorWithXFromLessThanXTo() {
        MathFunction source = x -> x * x; // Пример функции
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(3, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getX(1));
        assertEquals(3.0, function.getX(2));
        assertEquals(1.0, function.getY(0), 0.0001); // 1.0^2
        assertEquals(4.0, function.getY(1), 0.0001); // 2.0^2
        assertEquals(9.0, function.getY(2), 0.0001); // 3.0^2
    }
    @Test
    void testConstructorWithXFromGreaterThanXTo() {
        MathFunction source = x -> x * x; // Пример функции
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 3.0, 1.0, 3);

        assertEquals(3, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getX(1));
        assertEquals(3.0, function.getX(2));
        assertEquals(1.0, function.getY(0), 0.0001); // 1.0^2
        assertEquals(4.0, function.getY(1), 0.0001); // 2.0^2
        assertEquals(9.0, function.getY(2), 0.0001); // 3.0^2
    }
    @Test
    void testConstructorWithDifferentLengthArrays() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {2.0};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new LinkedListTabulatedFunction(xValues, yValues));
        assertEquals("Arrays must have the same length.", exception.getMessage());
    }

    @Test
    void testConstructorWithMathFunction() {
        MathFunction source = Math::sin;
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 0, Math.PI, 5);

        assertEquals(5, function.getCount());
        assertEquals(0.0, function.leftBound());
        assertEquals(Math.PI, function.rightBound());
    }

    @Test
    void testGetXAndY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1.0, function.getX(0));
        assertEquals(4.0, function.getY(1));
    }

    @Test
    void testSetY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        function.setY(1, 5.0);

        assertEquals(5.0, function.getY(1));
    }

    @Test
    void testIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1, function.indexOfX(2.0));
        assertEquals(-1, function.indexOfX(5.0));
    }

    @Test
    void testIndexOfY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1, function.indexOfY(4.0));
        assertEquals(-1, function.indexOfY(5.0));
    }

    @Test
    void testFloorIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1, function.floorIndexOfX(2.5));
        assertEquals(0, function.floorIndexOfX(1.0));
        assertEquals(-1, function.floorIndexOfX(0.0));
    }

    @Test
    void testInterpolate() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(4.0, function.interpolate(2.0)); // Значение при x = 2.0
        assertEquals(5.0, function.interpolate(2.5)); // Интерполяция
        assertEquals(2.0, function.interpolate(1.0)); // Экстраполяция влево
        assertEquals(3.0, function.interpolate(3.0)); // Экстраполяция вправо
    }

    @Test
    void testInterpolateWithSingleNode() {
        double[] xValues = {1.0};
        double[] yValues = {2.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(2.0, function.interpolate(1.0)); // Значение для единственного узла
    }
    @Test
    void testInterpolateExtrapolateLeft() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        // Проверка экстраполяции влево
        assertEquals(1.0, function.interpolate(0.5)); // Ожидается значение при x < 1.0 (leftBound)
    }

    @Test
    void testExtrapolate() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(2.0, function.extrapolate(0.5)); // Экстраполяция влево
        assertEquals(6.0, function.extrapolate(3.5)); // Экстраполяция вправо
    }

    @Test
    void testExtrapolateWithSingleNode() {
        double[] xValues = {1.0};
        double[] yValues = {2.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(2.0, function.extrapolate(0.0)); // Экстраполяция влево
        assertEquals(2.0, function.extrapolate(2.0)); // Экстраполяция вправо
    }

    @Test
    void testAddNode() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {2.0, 4.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(2, function.getCount());

        // Проверка, что значения добавлены корректно
        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getY(0));
    }

    @Test
    void testGetNode() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{2.0, 4.0, 6.0});

        assertDoesNotThrow(() -> function.getNode(1)); // Проверка на наличие узла
        assertEquals(2.0, function.getNode(1).x); // Проверка значения x
    }
    @Test
    void testInterpolateAccuracy() {
        double[] xValues = {1.0, 2.0, 3.0, 4.0};
        double[] yValues = {2.0, 4.0, 6.0, 8.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(5.0, function.interpolate(2.5), 0.0001); // Точное значение
    }
    @Test
    void testExtrapolateWithinBounds() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(4.0, function.extrapolate(2.0)); // Интерполяция при x = 2.0
        assertEquals(5.0, function.extrapolate(2.5)); // Интерполяция при x = 2.5
    }

}