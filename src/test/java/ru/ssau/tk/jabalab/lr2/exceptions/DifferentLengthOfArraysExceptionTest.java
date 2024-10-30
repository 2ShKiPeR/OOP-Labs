package ru.ssau.tk.jabalab.lr2.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DifferentLengthOfArraysExceptionTest {

    @Test
    void testConstructorWithoutParameters() {
        DifferentLengthOfArraysException exception = new DifferentLengthOfArraysException();
        assertEquals(null, exception.getMessage());
        assertTrue(exception instanceof RuntimeException); // Проверка наследования от RuntimeException
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Массивы имеют разную длину";
        DifferentLengthOfArraysException exception = new DifferentLengthOfArraysException(message);
        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException); // Проверка наследования от RuntimeException
    }
}
