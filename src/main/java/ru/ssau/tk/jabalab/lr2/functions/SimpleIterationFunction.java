package ru.ssau.tk.jabalab.lr2.functions;

public class SimpleIterationFunction {

    public static double solveEquation(double a, double b, double c, double x0, double eps) {
        double x1 = x0;
        do {
            x0 = x1;
            x1 = (b - a * x0) / c;
        } while (Math.abs(x1 - x0) > eps);
        return x1;
    }

    public static double Solution(String[] args) {
        double a = 2;
        double b = 5;
        double c = 3;
        double x0 = 1;
        double eps = 0.001;

        double solution = solveEquation(a, b, c, x0, eps);
        return solution;
    }
}
