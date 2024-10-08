package ru.ssau.tk.jabalab.lr2.functions;

import java.util.function.BiFunction;

public class ChaplyginMethodFunction implements MathFunction {
    private static final double LIPSCHITZ_CONSTANT = 1.0;  // Постоянная Липшица L = 1
    private double tolerance;  // Погрешность, при которой остановим итерации
    BiFunction<Double, Double, Double> f;    // ОДУ y' = f(x, y)
    double x0;
    double u0;
    double v0;
    int maxIterations;
    int steps;

    // Метод интегрирования методом прямоугольников
    public static double integrateRectangles(BiFunction<Double, Double, Double> func, double a, double b, int steps, double u_n) {
        double h = (b - a) / steps; // Шаг интегрирования
        double sum = 0.0;

        for (int i = 0; i < steps; i++) {
            double x = a + i * h; // Определяем левую границу подынтервала
            sum += func.apply(x, u_n); // Значение функции на левой границе
        }

        return sum * h; // Умножаем сумму значений функции на ширину подынтервала
    }

    ChaplyginMethodFunction(BiFunction<Double, Double, Double> f, double x0, double u0, double v0, int maxIterations, int steps, double tolerance) {
        this.f = f;
        this.x0 = x0;
        this.u0 = u0;
        this.v0 = v0;
        this.maxIterations = maxIterations;
        this.steps = steps;
        this.tolerance = tolerance;
    }

    // Метод Чаплыгина
    @Override
    public double apply(double x) {
        double u_n = u0;
        double v_n = v0;

        double lastU = u_n;
        double lastV = v_n;

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            double u_n1 = calculateNextU(x, u_n);
            double v_n1 = calculateNextV(x, v_n);

            // Усреднение значений
            u_n = (lastU + u_n1) / 2;
            v_n = (lastV + v_n1) / 2;

            // Проверяем условие завершения итераций
            if (Math.abs(v_n - u_n) < tolerance) {
                return (u_n + v_n) / 2;  // Полусумма приближений
            }

            lastU = u_n1;
            lastV = v_n1;
        }

        // Возвращаем результат после максимального числа итераций
        return (u_n + v_n) / 2;
    }

    double calculateNextU(double x, double u_prev) {
        return u_prev + integrateRectangles((t, u) ->
                        Math.exp(-LIPSCHITZ_CONSTANT * (x - t)) * (f.apply(t, u_prev) - derivative(f, t, u_prev)),
                x0, x, steps, u_prev);
    }

    double calculateNextV(double x, double v_prev) {
        return v_prev + integrateRectangles((t, v) ->
                        Math.exp(-LIPSCHITZ_CONSTANT * (x - t)) * (derivative(f, t, v_prev) - f.apply(t, v_prev)),
                x0, x, steps, v_prev);
    }

    // Приближенная производная (численно)
    public static double derivative(BiFunction<Double, Double, Double> f, double x, double y) {
        double h = 1e-8;
        return (f.apply(x, y + h) - f.apply(x, y - h)) / (2 * h);
    }
}