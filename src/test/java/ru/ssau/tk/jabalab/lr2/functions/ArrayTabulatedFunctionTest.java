package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTabulatedFunctionTest {

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
}