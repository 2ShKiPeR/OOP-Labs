package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {

    // Простая линейная функция: y = slope * x + intercept
    static class LinearFunction implements MathFunction {
        private final double slope;
        private final double intercept;

        public LinearFunction(double slope, double intercept) {
            this.slope = slope;
            this.intercept = intercept;
        }

        @Override
        public double apply(double x) {
            return slope * x + intercept;
        }
    }

    // Простая квадратная функция: y = x^2
    static class SquaredFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return x * x;
        }
    }

    // Простая кубическая функция: y = x^3
    static class CubicFunction implements MathFunction {
        @Override
        public double apply(double x) {
            return x * x * x;
        }
    }

    // Тест для проверки композиции линейной и квадратной функций
    @Test
    void testLinearAndSquaredFunction() {
        MathFunction linear = new LinearFunction(2, 3); // Функция: 2x + 3
        MathFunction squared = new SquaredFunction(); // Функция: x^2

        // Составная функция: k(x) = (2x + 3)^2
        CompositeFunction composite = new CompositeFunction(linear, squared);

        double input = 2; // Подставляем x = 2
        // Ожидаемое значение: (2 * 2 + 3)^2 = 7^2 = 49
        double expected = 49;
        assertEquals(expected, composite.apply(input), 0.001);
    }

    // Тест для проверки композиции квадратной и кубической функций
    @Test
    void testSquareAndCubicFunction() {
        MathFunction squared = new SquaredFunction(); // Функция: x^2
        MathFunction cubic = new CubicFunction(); // Функция: x^3

        // Составная функция: k(x) = (x^2)^3
        CompositeFunction composite = new CompositeFunction(squared, cubic);

        double input = 2; // Подставляем x = 2
        // Ожидаемое значение: (2^2)^3 = 4^3 = 64
        double expected = 64;
        assertEquals(expected, composite.apply(input), 0.001);
    }

    // Тест для проверки сложной композиции функций
    @Test
    void testComplexComposition() {
        MathFunction squared = new SquaredFunction(); // Функция: y = x^2
        MathFunction cubic = new CubicFunction(); // Функция: y = x^3

        // Первая составная функция: k1(x) = (x^2)^3
        CompositeFunction firstComposite = new CompositeFunction(squared, cubic);

        // Вторая составная функция: k2(x) = ((x^2)^3)^2
        CompositeFunction secondComposite = new CompositeFunction(firstComposite, squared);

        double input = 2; // Подставляем x = 2
        // Ожидаемое значение: (((2^2)^3)^2) = (4^3)^2 = (64)^2 = 4096
        double expected = Math.pow(Math.pow(4, 3), 2);
        assertEquals(expected, secondComposite.apply(input), 0.001);
    }
}