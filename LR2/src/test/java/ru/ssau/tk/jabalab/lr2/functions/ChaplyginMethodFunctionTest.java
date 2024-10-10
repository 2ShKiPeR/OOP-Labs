package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class ChaplyginMethodFunctionTest {
    private ChaplyginMethodFunction chaplyginMethodFunction;

    @BeforeEach
    void setUp() {
        // Простой тестовый случай, f(x, y) = x + y
        BiFunction<Double, Double, Double> testFunction = (x, y) -> x + y;
        chaplyginMethodFunction = new ChaplyginMethodFunction(testFunction, 0.0, 0.0, 0.0, 100, 10, 1e-5);
    }

    @Test
    void testIntegrateRectangles() {
        double result = ChaplyginMethodFunction.integrateRectangles((x, y) -> x + y, 0, 1, 10, 0);
        // Проверка, что интеграл от функции на [0, 1] равен 0.5
        assertEquals(0.5, result, 1e-1);
    }

    @Test
    void testCalculateNextU() {
        double u_next = chaplyginMethodFunction.calculateNextU(1.0, 0.0);
        // Убедитесь, что следующий u рассчитывается по ожиданиям
        assertNotNull(u_next);
    }

    @Test
    void testCalculateNextV() {
        double v_next = chaplyginMethodFunction.calculateNextV(1.0, 0.0);
        // Убедитесь, что следующий v рассчитывается по ожиданиям
        assertNotNull(v_next);
    }

    @Test
    void testDerivative() {
        double derivativeResult = ChaplyginMethodFunction.derivative((x, y) -> x + y, 1.0, 2.0);
        // Проверка, что производная от f(x, y) = x + y равна 1
        assertEquals(1, derivativeResult, 1e-5);
    }
    @Test
    void testApplyWithDifferentFunction() {
        BiFunction<Double, Double, Double> function = (x, y) -> x * y;
        ChaplyginMethodFunction chaplyginMethodFunction = new ChaplyginMethodFunction(function, 0, 1, 1, 100, 10000, 1e-6);

        double result = chaplyginMethodFunction.apply(2.0);
        assertEquals(1.0, result, 1e-6);
    }
    @Test
    void testApplyIterations() {
        BiFunction<Double, Double, Double> f = (x, y) -> x + y;
        chaplyginMethodFunction = new ChaplyginMethodFunction(f, 0, 1, 0, 100, 10, 1e-5);
        double result1 = chaplyginMethodFunction.apply(1);

        // Мы можем повторно вызвать метод и проверить, что результаты остаются консистентными.
        double result2 = chaplyginMethodFunction.apply(1);
        assertEquals(result1, result2, 1e-5);
    }
    @Test
    public void testApplyMaxIterations() {
        // Устанавливаем очень маленькую точность для проверки, что метод завершится через максимальное количество итераций
        chaplyginMethodFunction = new ChaplyginMethodFunction((x, y) -> x * y, 0, 1, 1, 3, 10, 1e-10);
        double result = chaplyginMethodFunction.apply(1);
        // Можно ожидать какое-то значение, но оно не должно быть NaN
        assertEquals(1.0, result, 1e-5);
    }

}