package ru.ssau.tk.jabalab.lr2.functions.factory;

import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}