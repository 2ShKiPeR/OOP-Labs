package ru.ssau.tk.jabalab.lr2.functions;

public class ChaplyginFunction implements MathFunction {
    @Override
    public double apply(double x) {
        // Пример реализации: y = sin(x)
        return Math.sin(x);
    }

    public double[] apply(double[] xValues) {
        double[] results = new double[xValues.length];
        for (int i = 0; i < xValues.length; i++) {
            results[i] = apply(xValues[i]);
        }
        return results;
    }
}
