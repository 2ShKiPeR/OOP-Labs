package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayTabulatedFunctionTest {

    // Внутренний класс для тестирования MathFunction
    private static class TestMathFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return 2 * x; // Простая линия y = 2x
        }
    }

    // Тестирование конструктора с MathFunction
    @Test
    void testConstructorWithMathFunction() {
        MathFunction source = new TestMathFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 1.0, 5.0, 5);

        assertEquals(5, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(5.0, function.getX(4));
        assertEquals(2.0, function.getY(0)); // y = 2 * 1
        assertEquals(10.0, function.getY(4)); // y = 2 * 5
    }

    // Тестирование конструктора с перевернутыми границами
    @Test
    void testConstructorWithReversedRange() {
        MathFunction source = new TestMathFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 5.0, 1.0, 5);

        assertEquals(5, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(5.0, function.getX(4));
    }

    // Тестирование конструктора с одной точкой
    @Test
    void testConstructorWithSinglePoint() {
        MathFunction source = new TestMathFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 2.0, 2.0, 1);

        assertEquals(1, function.getCount());
        assertEquals(2.0, function.getX(0));
        assertEquals(4.0, function.getY(0)); // y = 2 * 2
    }

    // Тестирование конструктора с count равным 1
    @Test
    void testConstructorWithCountOne() {
        MathFunction source = new TestMathFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 0.0, 0.0, 1);
        assertEquals(1, function.getCount());
        assertEquals(0.0, function.getX(0));
        assertEquals(0.0, function.getY(0)); // y = 2 * 0
    }

    // Существующие тесты для вставки и других методов
    @Test
    void testInsert() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // Вставка нового значения
        function.insert(2.5, 6.25); // Добавление между 2 и 3
        assertEquals(9, function.getY(3), 0.0001); // Проверяем, что значение вставлено правильно
        assertEquals(4, function.getCount()); // Проверяем, что количество элементов увеличилось

        // Обновление существующего значения
        function.insert(2.0, 5.0);
        assertEquals(5.0, function.getY(1), 0.0001); // Проверяем, что 2.0 теперь соответствует 5.0

        // Вставка значения меньше всех существующих
        function.insert(0.5, 0.25);
        assertEquals(0.25, function.getY(0), 0.0001); // Проверяем, что значение вставлено в начало
        assertEquals(5, function.getCount()); // Проверка увеличения количества элементов

        // Вставка значения больше всех существующих
        function.insert(4.0, 16.0);
        assertEquals(16.0, function.getY(5), 0.0001); // Проверяем, что значение вставлено в конец
        assertEquals(6, function.getCount()); // Проверка увеличения количества элементов
    }

    @Test
    void testInsertWithInitialCapacity() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {1.0, 4.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // Вставляем новые значения, чтобы проверить использование "резервных" ячеек
        for (int i = 3; i <= 12; i++) {
            function.insert((double) i, Math.pow(i, 2));
        }

        assertEquals(12, function.getCount()); // Проверяем, что количество элементов увеличилось
    }

    @Test
    void getYTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        assertEquals(3, func.getX(2));
    }

    @Test
    void setYTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        func.setY(2, 5);
        assertEquals(5, func.getY(2));
    }

    @Test
    void floorIndexOfXTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        assertEquals(4, func.floorIndexOfX(5));
    }

    @Test
    void leftBoundTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        assertEquals(1, func.leftBound());
    }

    @Test
    void rightBoundTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        assertEquals(5, func.rightBound());
    }

    @Test
    void getXTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        assertEquals(4, func.getX(3));
    }

    @Test
    void extrapolateLeftTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        assertEquals(arrY[0] + (arrY[1] - arrY[0]) / (arrX[1] - arrX[0]) * (-1 - arrX[0]), func.apply(-1));
    }

    @Test
    void extrapolateRightTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        assertEquals(arrY[func.getCount() - 2] + (arrY[func.getCount() - 1] - arrY[func.getCount() - 2]) / (arrX[func.getCount() - 1] - arrX[func.getCount() - 2]) * (6 - arrX[func.getCount() - 2]), func.apply(6));
    }

    @Test
    void interpolateTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);
        int floorIndex = func.floorIndexOfX(4.5);
        assertEquals(arrY[floorIndex] + (arrY[floorIndex + 1] - arrY[floorIndex]) / (arrX[floorIndex + 1] - arrX[floorIndex]) * (4.5 - arrX[floorIndex]), func.apply(4.5));
    }
    @Test
    void indexOfYTest() {
        double[] arrX = {1, 2, 3, 4, 5};
        double[] arrY = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);

        // Тестирование существующих значений
        assertEquals(0, func.indexOfY(1)); // индекс Y = 1
        assertEquals(2, func.indexOfY(3)); // индекс Y = 3
        assertEquals(4, func.indexOfY(5)); // индекс Y = 5

        // Тестирование отсутствующих значений
        assertEquals(-1, func.indexOfY(6)); // Y = 6 не существует
        assertEquals(-1, func.indexOfY(-1)); // Y = -1 не существует
    }
}