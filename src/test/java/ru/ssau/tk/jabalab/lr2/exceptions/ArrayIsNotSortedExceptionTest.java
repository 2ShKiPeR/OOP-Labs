package ru.ssau.tk.jabalab.lr2.exceptions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.exceptions.ArrayIsNotSortedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayIsNotSortedExceptionTest {

    @Test
    void testConstructorWithoutParameters() {
        ArrayIsNotSortedException exception = new ArrayIsNotSortedException();
        assertEquals(null, exception.getMessage()); // Проверяем, что сообщение пустое
    }

    @Test
    void testConstructorWithMessage() {
        ArrayIsNotSortedException exception = new ArrayIsNotSortedException("Массив не отсортирован");
        assertEquals("Массив не отсортирован", exception.getMessage()); // Проверяем, что сообщение совпадает с ожидаемым
    }
}