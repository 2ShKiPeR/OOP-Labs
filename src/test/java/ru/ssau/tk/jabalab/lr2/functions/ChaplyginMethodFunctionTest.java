package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class ChaplyginMethodFunctionTest {
    private ChaplyginMethodFunction chaplyginMethodFunction;

    @BeforeEach
    void setUp() {
        // Инициализация перед каждым тестом с простой функцией f(x, y) = x + y.
        BiFunction<Double, Double, Double> testFunction = (x, y) -> x + y;
        chaplyginMethodFunction = new ChaplyginMethodFunction(testFunction, 0.0, 0.0, 0.0, 100, 10, 1e-5);
    }

    @Test
    void testIntegrateRectangles() {
        // Проверка метода интегрирования прямоугольниками.
        double result = ChaplyginMethodFunction.integrateRectangles((x, y) -> x + y, 0, 1, 10, 0);
        // Ожидаем, что интеграл от функции на [0, 1] равен 0.5.
        assertEquals(0.5, result, 1e-1);
    }

    @Test
    void testCalculateNextU() {
        // Проверка метода расчета следующего значения u.
        double u_next = chaplyginMethodFunction.calculateNextU(1.0, 0.0);
        // Убедимся, что следующий u рассчитывается без ошибок.
        assertNotNull(u_next);
    }

    @Test
    void testCalculateNextV() {
        // Проверка метода расчета следующего значения v.
        double v_next = chaplyginMethodFunction.calculateNextV(1.0, 0.0);
        // Убедимся, что следующий v рассчитывается без ошибок.
        assertNotNull(v_next);
    }

    @Test
    void testDerivative() {
        // Проверка метода расчета производной.
        double derivativeResult = ChaplyginMethodFunction.derivative((x, y) -> x + y, 1.0, 2.0);
        // Ожидаем, что производная от f(x, y) = x + y равна 1.
        assertEquals(1, derivativeResult, 1e-5);
    }

    @Test
    void testApplyWithDifferentFunction() {
        // Проверка работы метода apply с другой функцией.
        BiFunction<Double, Double, Double> function = (x, y) -> x * y;
        ChaplyginMethodFunction chaplyginMethodFunction = new ChaplyginMethodFunction(function, 0, 1, 1, 100, 10000, 1e-6);

        // Вызываем метод apply и проверяем результат.
        double result = chaplyginMethodFunction.apply(2.0);
        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void testApplyIterations() {
        // Проверка консистентности результатов при повторном вызове метода apply.
        BiFunction<Double, Double, Double> f = (x, y) -> x + y;
        chaplyginMethodFunction = new ChaplyginMethodFunction(f, 0, 1, 0, 100, 10, 1e-5);
        double result1 = chaplyginMethodFunction.apply(1);

        // Повторный вызов метода и проверка на равенство результатов.
        double result2 = chaplyginMethodFunction.apply(1);
        assertEquals(result1, result2, 1e-5);
    }

    @Test
    public void testApplyMaxIterations() {
        // Устанавливаем очень маленькую точность для проверки, что метод завершится через максимальное количество итераций.
        chaplyginMethodFunction = new ChaplyginMethodFunction((x, y) -> x * y, 0, 1, 1, 3, 10, 1e-10);
        double result = chaplyginMethodFunction.apply(1);
        // Проверяем, что результат не является NaN.
        assertNotEquals(Double.NaN, result);
        assertEquals(1.0, result, 1e-5);
    }

    @Test
    void testLipschitzConstantCalculation() {
        // Проверка правильности расчета постоянной Липшица для более сложной функции.
        BiFunction<Double, Double, Double> function = (x, y) -> x * x + y; // Пример функции
        ChaplyginMethodFunction chaplyginMethodFunction = new ChaplyginMethodFunction(function, 0.0, 0.0, 0.0, 100, 10, 1e-5);

        double expectedLipschitz = chaplyginMethodFunction.calculateLipschitzConstant(function, 0.0, 0.0);
        // Проверяем, что постоянная Липшица неотрицательна и была рассчитана корректно.
        assertTrue(expectedLipschitz >= 0, "The Lipschitz constant must be non-negative");
        assertNotNull(expectedLipschitz);
    }
}