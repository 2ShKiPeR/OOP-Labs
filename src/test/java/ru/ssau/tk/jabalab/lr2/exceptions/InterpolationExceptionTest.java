package ru.ssau.tk.jabalab.lr2.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterpolationExceptionTest {

    @Test
    void testConstructorWithoutParameters() {
        InterpolationException exception = new InterpolationException();
        assertEquals(null, exception.getMessage());
        assertTrue(exception instanceof RuntimeException); // Проверка наследования от RuntimeException
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Ошибка интерполяции";
        InterpolationException exception = new InterpolationException(message);
        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException); // Проверка наследования от RuntimeException
    }
}
