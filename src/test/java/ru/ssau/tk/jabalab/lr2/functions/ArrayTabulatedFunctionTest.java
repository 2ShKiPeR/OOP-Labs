package ru.ssau.tk.jabalab.lr2.functions;

import ru.ssau.tk.jabalab.lr2.exceptions.InterpolationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;

public class ArrayTabulatedFunctionTest {

    // Внутренний класс для тестирования MathFunction
    private static class TestMathFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return 2 * x; // Простая линия y = 2x
        }
    }

    @Test
    void testConstructorWithTooFewPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArrayTabulatedFunction(new double[]{1.0}, new double[]{2.0});
        });
    }

    // Тестирование getX с некорректным индексом
    @Test
    void testGetXWithInvalidIndex() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {1.0, 4.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(2));
    }

    // Тестирование getY с некорректным индексом
    @Test
    void testGetYWithInvalidIndex() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {1.0, 4.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(2));
    }

    // Тестирование floorIndexOfX с некорректным значением x
    @Test
    void testFloorIndexOfXWithOutOfBoundsX() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {1.0, 4.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertThrows(IllegalArgumentException.class, () -> function.floorIndexOfX(0.0));
        assertThrows(IllegalArgumentException.class, () -> function.floorIndexOfX(3.0));
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
        ArrayTabulatedFunction list = new ArrayTabulatedFunction(arrX, arrY);
        int floorIndex = list.floorIndexOfX(4.5);
        assertThrows(InterpolationException.class, () -> list.interpolate(1.5, 1));
        assertDoesNotThrow(() -> list.interpolate(1.5, 0));
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

    @Test
    void iteratorTest1(){
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        Iterator<Point> iterator = arr.iterator();
        int j = 0;
        while(iterator.hasNext()){
            Point point = iterator.next();
            assertEquals(point.x, arr.getX(j));
            assertEquals(point.y, arr.getY(j++));
        }
    }

    @Test
    void iteratorTest2(){
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        int j = 0;
        for (Point point : arr) {
            assertEquals(point.x, arr.getX(j));
            assertEquals(point.y, arr.getY(j++));
        }
    }
}