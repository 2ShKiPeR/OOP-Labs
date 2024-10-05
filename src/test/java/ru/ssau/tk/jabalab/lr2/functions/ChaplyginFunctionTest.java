package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChaplyginMethodTest {
    private ChaplyginMethod chaplyginMethod;

    // Инициализация объекта ChaplyginMethod перед каждым тестом
    @BeforeEach
    void setUp() {
        chaplyginMethod = new ChaplyginMethod(1.0, 1.0, 10, 10);
    }
    // Тестирование инициализации объекта
    @Test
    void testInitialization() {
        assertEquals(1.0, chaplyginMethod.getL());
        assertEquals(1.0, chaplyginMethod.getT());
        assertEquals(10, chaplyginMethod.getNx());
        assertTrue(chaplyginMethod.getDx() > 0, "Шаг dx должен быть положительным");
    }

    // Тестирование метода apply для значения в допустимом диапазоне
    @Test
    void testApplyWithinRange() {
        double result = chaplyginMethod.apply(0.5);
        assertFalse(Double.isNaN(result), "Результат не должен быть NaN");
    }

    // Тестирование метода apply для значения ниже допустимого диапазона
    @Test
    void testApplyOutOfLowerRange() {
        assertThrows(IllegalArgumentException.class, () -> chaplyginMethod.apply(-0.1),
                "Должно выбросить IllegalArgumentException для xPosition вне диапазона"
        );
    }

    // Тестирование метода apply для значения выше допустимого диапазона
    @Test
    void testApplyOutOfUpperRange() {
        assertThrows(IllegalArgumentException.class, () -> chaplyginMethod.apply(1.1),
                "Должно выбросить IllegalArgumentException для xPosition вне диапазона"
        );
    }

    // Тестирование метода getL на правильное значение
    @Test
    void testGetL() {
        assertEquals(1.0, chaplyginMethod.getL());
    }

    // Тестирование метода getT на правильное значение
    @Test
    void testGetT() {
        assertEquals(1.0, chaplyginMethod.getT());
    }

    // Тестирование метода getNx на выполнение условия Nx > 2
    @Test
    void testGetNx() {
        assertTrue(chaplyginMethod.getNx() > 2, "Nx должно быть больше 2");
    }

    // Тестирование метода getDx на выполнение условия dx > 0
    @Test
    void testGetDx() {
        assertTrue(chaplyginMethod.getDx() > 0, "dx должно быть больше 0");
    }
}

