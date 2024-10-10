package ru.ssau.tk.jabalab.lr2.functions;

import java.util.function.BiFunction;

public class ChaplyginMethodFunction implements MathFunction {
    private double lipschitzConstant;  // Постоянная Липшица, используемая для оценки сходимости
    private double tolerance;            // Погрешность, определяющая точность результата
    BiFunction<Double, Double, Double> f; // Функция, представляющая уравнение в частных производных y' = f(x, y)
    double x0;                          // Начальное значение переменной x
    double u0;                          // Начальное значение u (возможно, начальное значение y)
    double v0;                          // Начальное значение v
    int maxIterations;                  // Максимальное количество итераций для метода
    int steps;                          // Количество шагов для численного интегрирования

    // Метод для численного интегрирования с использованием прямоугольников
    public static double integrateRectangles(BiFunction<Double, Double, Double> func, double a, double b, int steps, double u_n) {
        double h = (b - a) / steps; // Шаг интегрирования
        double sum = 0.0;            // Сумма, инициализируемая нулем
        for (int i = 0; i < steps; i++) {
            double x = a + i * h; // Левая граница подынтервала
            sum += func.apply(x, u_n); // Значение функции на левой границе
        }
        return sum * h; // Умножаем сумму на ширину подынтервала для получения результата интегрирования
    }

    // Конструктор, инициализирующий значения и рассчитывающий постоянную Липшица
    public ChaplyginMethodFunction(BiFunction<Double, Double, Double> f, double x0, double u0, double v0, int maxIterations, int steps, double tolerance) {
        this.f = f;
        this.x0 = x0;
        this.u0 = u0;
        this.v0 = v0;
        this.maxIterations = maxIterations;
        this.steps = steps;
        this.tolerance = tolerance;
        this.lipschitzConstant = calculateLipschitzConstant(f, x0, u0); // Рассчитываем и устанавливаем постоянную Липшица
    }

    @Override
    public double apply(double x) {
        double u_n = u0;    // Инициализируем текущее значение u
        double v_n = v0;    // Инициализируем текущее значение v
        double lastU = u_n; // Предыдущее значение u
        double lastV = v_n; // Предыдущее значение v

        // Итерационный процесс для вычисления значений u и v
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            double u_n1 = calculateNextU(x, u_n); // Вычисляем следующее значение u
            double v_n1 = calculateNextV(x, v_n); // Вычисляем следующее значение v

            // Обновляем текущее значение u и v, используя полусумму
            u_n = (lastU + u_n1) / 2;
            v_n = (lastV + v_n1) / 2;

            // Проверяем, достигнута ли заданная точность
            if (Math.abs(v_n - u_n) < tolerance) {
                return (u_n + v_n) / 2; // Возвращаем полусумму, если достигнута сходимость
            }

            lastU = u_n1; // Обновляем предыдущее значение u
            lastV = v_n1; // Обновляем предыдущее значение v
        }
        return (u_n + v_n) / 2; // Возвращаем полусумму после достижения максимального числа итераций
    }

    // Метод для расчета следующего значения u на основе текущего значения
    double calculateNextU(double x, double u_prev) {
        return u_prev + integrateRectangles((t, u) ->
                        Math.exp(-lipschitzConstant * (x - t)) * (f.apply(t, u_prev) - derivative(f, t, u_prev)),
                x0, x, steps, u_prev);
    }

    // Метод для расчета следующего значения v на основе текущего значения
    double calculateNextV(double x, double v_prev) {
        return v_prev + integrateRectangles((t, v) ->
                        Math.exp(-lipschitzConstant * (x - t)) * (derivative(f, t, v_prev) - f.apply(t, v_prev)),
                x0, x, steps, v_prev);
    }

    // Метод для вычисления производной функции f по переменным x и y
    public static double derivative(BiFunction<Double, Double, Double> f, double x, double y) {
        double h = 1e-8; // Шаг для приближения производной
        return (f.apply(x, y + h) - f.apply(x, y - h)) / (2 * h); // Формула центральной разности
    }

    // Метод для вычисления постоянной Липшица функции f
    double calculateLipschitzConstant(BiFunction<Double, Double, Double> f, double x, double u) {
        // Пример простой оценки постоянной Липшица через производную
        double dh = 1e-5; // Малое значение для улучшения аппроксимации
        double derivativeAtUPlus = derivative(f, x, u + dh); // Производная при увеличении u
        double derivativeAtUMinus = derivative(f, x, u - dh); // Производная при уменьшении u
        return Math.max(Math.abs(derivativeAtUPlus), Math.abs(derivativeAtUMinus)); // Максимум из абсолютных значений производных
    }
}
