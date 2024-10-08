package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTabulatedFunctionTest {
    private LinkedListTabulatedFunction function;
    @BeforeEach
    public void setUp() {
        // Инициализация некоторыми значениями для использования в тестах
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        function = new LinkedListTabulatedFunction(xValues, yValues);
    }

    // Тестирование конструктора с валидными массивами
    @Test
    public void testConstructorWithValidArrays() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues = {1.0, 2.0, 3.0};
        LinkedListTabulatedFunction validFunction = new LinkedListTabulatedFunction(xValues, yValues);
        // Проверка, что количество узлов соответствует ожиданиям
        assertEquals(3, validFunction.getCount());
    }

    // Тестирование конструктора с несоответствующими размерами массивов
    @Test
    public void testConstructorWithMismatchedArrays() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {2.0};
        // Проверка, что выбрасывается исключение IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new LinkedListTabulatedFunction(xValues, yValues);
        });
    }

    // Тестирование конструктора с пустыми массивами
    @Test
    public void testConstructorWithEmptyArrays() {
        double[] xValues = {};
        double[] yValues = {};
        // Проверка, что выбрасывается исключение IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new LinkedListTabulatedFunction(xValues, yValues);
        });
    }

    // Тестирование метода getCount
    @Test
    public void testGetCount() {
        assertEquals(3, function.getCount());
    }

    // Тестирование метода leftBound
    @Test
    public void testLeftBound() {
        assertEquals(1.0, function.leftBound());
    }

    // Тестирование метода rightBound
    @Test
    public void testRightBound() {
        assertEquals(3.0, function.rightBound());
    }

    // Тестирование метода leftBound для пустого списка
    @Test
    public void testLeftBoundWithEmptyList() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction();

        double result = function.leftBound();

        // Ожидаем, что результат - NaN
        assertTrue(Double.isNaN(result));
    }

    // Тестирование метода rightBound для пустого списка
    @Test
    public void testRightBoundWithEmptyList() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction();

        double result = function.rightBound();

        // Ожидаем, что результат - NaN
        assertTrue(Double.isNaN(result));
    }

    // Тестирование метода getX по индексам
    @Test
    public void testGetX() {
        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getX(1));
        assertEquals(3.0, function.getX(2));

        // Проверка обработки исключений на неверных индексах
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(3));
    }

    // Тестирование метода getY по индексам
    @Test
    public void testGetY() {
        assertEquals(2.0, function.getY(0));
        assertEquals(4.0, function.getY(1));
        assertEquals(6.0, function.getY(2));

        // Проверка обработки исключений на неверных индексах
        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(3));
    }

    // Тестирование метода setY
    @Test
    public void testSetY() {
        function.setY(1, 5.0);
        // Проверка изменения значения Y для узла
        assertEquals(5.0, function.getY(1));
    }

    // Тестирование метода indexOfX
    @Test
    public void testIndexOfX() {
        assertEquals(0, function.indexOfX(1.0));
        assertEquals(1, function.indexOfX(2.0));
        assertEquals(2, function.indexOfX(3.0));
        // Проверка на отсутствие значения
        assertEquals(-1, function.indexOfX(4.0));
    }

    // Тестирование метода indexOfY
    @Test
    public void testIndexOfY() {
        assertEquals(0, function.indexOfY(2.0));
        assertEquals(1, function.indexOfY(4.0));
        assertEquals(2, function.indexOfY(6.0));
        // Проверка на отсутствие значения
        assertEquals(-1, function.indexOfY(7.0));
    }

    // Тестирование метода floorIndexOfX
    @Test
    public void testFloorIndexOfX() {
        assertEquals(0, function.floorIndexOfX(1.0)); // Точное соответствие
        assertEquals(1, function.floorIndexOfX(2.5)); // Между 2.0 и 3.0
        assertEquals(2, function.floorIndexOfX(3.0)); // Точное соответствие
        assertEquals(2, function.floorIndexOfX(4.0)); // Больше всех значений
        assertEquals(-1, function.floorIndexOfX(0.5)); // Меньше минимального значения
    }

    // Тестирование метода extrapolateLeft
    @Test
    public void testExtrapolateLeft() {
        assertEquals(2.0, function.extrapolateLeft(0.5)); // Ожидаемое значение для экстраполяции
    }

    // Тестирование метода extrapolateRight
    @Test
    public void testExtrapolateRight() {
        assertEquals(6.0, function.extrapolateRight(4.0)); // Ожидаемое значение для экстраполяции
    }

    // Тестирование метода interpolate
    @Test
    public void testInterpolate() {
        assertEquals(3.0, function.interpolate(1.5, 0), 0.01); // Интерполяция между 1.0 и 2.0
        assertEquals(5.0, function.interpolate(2.5, 1), 0.01); // Интерполяция между 2.0 и 3.0
    }

    // Тестирование метода interpolate с одним узлом
    @Test
    public void testInterpolateWithSingleNode() {
        // Инициализация с одним узлом
        double[] xValues = {1.0}; // Один узел
        double[] yValues = {2.0}; // Значение Y для этого узла
        function = new LinkedListTabulatedFunction(xValues, yValues);

        // Вызов метода interpolate
        double result = function.interpolate(1.5, 0); // x может быть любым, индекс должен быть 0

        // Проверка результата
        assertEquals(2.0, result, 0.001); // Результат должен равняться Y, который равен 2.0
    }

    // Тестирование метода interpolate с использованием floorNode
    @Test
    public void testInterpolateOnlyWithFloorNode() {
        // Инициализация с несколькими узлами
        double[] xValues = {1.0, 2.0, 3.0}; // Три узла
        double[] yValues = {2.0, 4.0, 6.0}; // Соответствующие Y значения
        function = new LinkedListTabulatedFunction(xValues, yValues);

        // Переполнение индекса, должно использоваться floorNode
        double result = function.interpolate(3.5, 2); // floorIndex равен 2 (последний узел)

        // Проверка результата. Должно быть 6.0, так как узел ceilingNode не существует.
        assertEquals(6.0, result, 0.001);
    }

    // Тестирование метода получения узла из конца списка
    @Test
    public void testGetNodeFromEnd() {
        // Инициализация с 5 элементами
        double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yValues = {2.0, 4.0, 6.0, 8.0, 10.0};
        function = new LinkedListTabulatedFunction(xValues, yValues);

        // Тесты для индексов, превышающих половину размера списка
        assertEquals(5.0, function.getX(4));  // последний элемент (индекс 4)
        assertEquals(4.0, function.getX(3));  // предпоследний элемент (индекс 3)
    }

    // Новые тесты для конструктора из MathFunction
    private static class TestMathFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return 2 * x; // Простая линия y = 2x
        }
    }

    // Тестирование конструктора из MathFunction с валидными параметрами
    @Test
    public void testConstructorFromMathFunctionValid() {
        MathFunction source = new TestMathFunction();
        LinkedListTabulatedFunction functionFromSource = new LinkedListTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(3, functionFromSource.getCount());
        assertEquals(1.0, functionFromSource.getX(0));
        assertEquals(2.0, functionFromSource.getX(1));
        assertEquals(3.0, functionFromSource.getX(2));
        assertEquals(2.0, functionFromSource.getY(0)); // y = 2 * 1
        assertEquals(4.0, functionFromSource.getY(1)); // y = 2 * 2
        assertEquals(6.0, functionFromSource.getY(2)); // y = 2 * 3
    }

    // Тестирование конструктора из MathFunction с количеством меньше единицы
    @Test
    public void testConstructorFromMathFunctionCountLessThanOne() {
        MathFunction source = new TestMathFunction();
        // Проверка, что выбрасывается IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new LinkedListTabulatedFunction(source, 1.0, 3.0, 0);
        });
    }

    // Тестирование конструктора из MathFunction, когда xFrom больше xTo
    @Test
    public void testConstructorFromMathFunctionXFromGreaterThanXTo() {
        MathFunction source = new TestMathFunction();
        LinkedListTabulatedFunction functionFromSource = new LinkedListTabulatedFunction(source, 3.0, 1.0, 3);

        assertEquals(3, functionFromSource.getCount());
        assertEquals(1.0, functionFromSource.getX(0));
        assertEquals(2.0, functionFromSource.getX(1));
        assertEquals(3.0, functionFromSource.getX(2));
        assertEquals(2.0, functionFromSource.getY(0)); // y = 2 * 1
        assertEquals(4.0, functionFromSource.getY(1)); // y = 2 * 2
        assertEquals(6.0, functionFromSource.getY(2)); // y = 2 * 3
    }
}

