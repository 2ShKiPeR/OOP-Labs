package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.jabalab.lr2.exceptions.InterpolationException;
import java.util.Iterator;

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
        assertThrows(DifferentLengthOfArraysException.class, () -> {
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

    // Тестирование метода interpolate с использованием floorNode
    @Test
    void interpolateFloorTest(){
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        assertThrows(InterpolationException.class, () -> list.interpolate(1.5, 1));
        assertDoesNotThrow(() -> list.interpolate(1.5, 0));
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

    @Test
    public void testRemoveValidIndex() {
        function.remove(1); // Удаляем элемент с индексом 1 (2.0, 4.0, 6.0)
        assertEquals(2, function.getCount()); // Проверяем количество элементов
        assertEquals(1.0, function.getX(0)); // Проверяем оставшийся элемент
        assertEquals(6.0, function.getY(1)); // Проверяем, что 6.0 остался на месте, индекс 1
    }

    // Тестирование метода remove для удаления головы списка
    @Test
    public void testRemoveHead() {
        function.remove(0); // Удаляем голову (значение 1.0)
        assertEquals(2, function.getCount()); // Количество должно уменьшиться
        assertEquals(2.0, function.getX(0)); // Теперь голова должна быть 2.0
    }

    // Тестирование метода remove для удаления последнего элемента
    @Test
    public void testRemoveLastElement() {
        function.remove(2); // Удаляем последний элемент
        assertEquals(2, function.getCount()); // Количество должно уменьшиться
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(2)); // Проверяем, что доступ к индексу 2 выбрасывает исключение
    }

    // Тестирование метода remove с индексом вне границ
    @Test
    public void testRemoveIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(-1)); // Неверный индекс
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(3)); // Неверный индекс (больше, чем размер)
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
    // Тестирование метода insert, когда список пустой
    @Test
    public void testInsertIntoEmptyList() {
        function.remove(0); // Удаляем все элементы, чтобы оставить список пустым
        function.insert(1.0, 1.0); // Вставляем значение в пустой список
        assertEquals(3, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(1.0, function.getY(0));
    }

    // Тестирование вставки перед головой
    @Test
    public void testInsertBeforeHead() {
        function.insert(0.5, 1.5); // Вставить перед текущей головой (1.0)
        assertEquals(4, function.getCount());
        assertEquals(0.5, function.getX(0)); // Теперь голова должна быть 0.5
    }

    // Тестирование вставки с одинаковым значением X
    @Test
    public void testInsertWithEqualX() {
        function.insert(2.0, 5.0); // Заменяем y для уже существующего x (2.0)
        assertEquals(3, function.getCount()); // Количество должно остаться прежним
        assertEquals(5.0, function.getY(1)); // Значение y для x=2.0 должно быть обновлено
    }

    // Тестирование вставки в конец списка
    @Test
    public void testInsertAtEnd() {
        function.insert(4.0, 8.0); // Добавляем значение в конец списка
        assertEquals(4, function.getCount());
        assertEquals(4.0, function.getX(3)); // Новое значение должно быть в конце
    }

    // Тестирование вставки между существующими узлами
    @Test
    public void testInsertBetweenNodes() {
        function.insert(1.5, 3.0); // Вставляем между 1.0 и 2.0
        assertEquals(4, function.getCount());
        assertEquals(1.5, function.getX(1)); // Проверяем, что 1.5 теперь на 1 позиции
    }

    // Тестирование вставки значения перед узлом с меньшим x
    @Test
    public void testInsertWithSmallerX() {
        function.insert(0.0, 0.0); // Вставляем значение меньше всех существующих
        assertEquals(4, function.getCount());
        assertEquals(0.0, function.getX(0)); // Проверяем, что 0.0 теперь в начале
    }

    // Тестирование вставки значения больше всех
    @Test
    public void testInsertWithGreaterX() {
        function.insert(5.0, 10.0); // Вставляем значение больше всех существующих
        assertEquals(4, function.getCount());
        assertEquals(5.0, function.getX(3)); // Проверяем, что новое значение добавлено в конец
    }


    // Тестирование вставки между узлами со значениями
    @Test
    public void testInsertBetweenExistingNodes() {
        function.insert(2.5, 5.0); // Вставка между 2.0 и 3.0
        assertEquals(3, function.getCount());
        assertEquals(3.0, function.getX(2)); // Проверяем, что 2.5 теперь на 2 позиции
    }

    // Тестирование вставки с использованием существующего узла
    @Test
    public void testInsertWithExistingX() {
        function.insert(1.0, 10.0); // Заменяем y для x (1.0)
        assertEquals(3, function.getCount()); // Количество должно остаться прежним
        assertEquals(10.0, function.getY(0)); // Проверяем, что y обновилось
    }

    // Тестирование исключений
    @Test
    public void testGetXOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(3));
    }

    @Test
    public void testConstructorWithMinimumPoints() {
        double[] xValues = {1.0}; // Один узел
        double[] yValues = {2.0};
        assertThrows(IllegalArgumentException.class, () -> {
            new LinkedListTabulatedFunction(xValues, yValues);
        });
    }
    @Test
    public void testIteratorWhile() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.1, 2.1, 3.1};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        Iterator<Point> iterator = function.iterator();

        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertNotNull(point);
        }
    }

    @Test
    public void testIteratorForEach() {
        double[] xValues = {4.0, 5.0, 6.0};
        double[] yValues = {4.1, 5.1, 6.1};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        for (Point point : function) {
            assertNotNull(point);
        }
    }
    // Тестирование метода getHead
    @Test
    public void testGetHead() {
        LinkedListTabulatedFunction emptyFunction = new LinkedListTabulatedFunction();
        assertNull(emptyFunction.getHead()); // Проверка, что голова списка пустого объекта равна null

        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        function = new LinkedListTabulatedFunction(xValues, yValues);

        // Проверка, что голова не равна null
        assertNotNull(function.getHead());

        // Проверка, что значение x головы совпадает с первым значением массива xValues
        // и значение y соответствует первому значению массива yValues, используя getX и getY
        assertEquals(1.0, function.getX(0)); // Проверка, что значение x головы списка соответствует первому значению
        assertEquals(2.0, function.getY(0)); // Проверка, что значение y головы списка соответствует первому значению
    }

}

