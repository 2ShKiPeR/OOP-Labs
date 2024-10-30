package ru.ssau.tk.jabalab.lr2.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InconsistentFunctionsExceptionTest {

    @Test
    void testConstructorWithoutParameters() {
        InconsistentFunctionsException exception = new InconsistentFunctionsException();
        assertEquals(null, exception.getMessage());
        assertTrue(exception instanceof RuntimeException); // Проверка наследования от RuntimeException
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Функции несовместимы";
        InconsistentFunctionsException exception = new InconsistentFunctionsException(message);
        assertEquals(message, exception.getMessage());
        assertTrue(exception instanceof RuntimeException); // Проверка наследования от RuntimeException
    }
}

