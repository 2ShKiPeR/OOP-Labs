package ru.ssau.tk.jabalab.lr2.operations;

import ru.ssau.tk.jabalab.lr2.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.Point;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    // Конструктор по умолчанию - использует ArrayTabulatedFunctionFactory
    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    // Конструктор с передачей конкретной фабрики
    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];

        // Заполнение массивов x и y
        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i].x;
        }

        // Вычисляем значения y с использованием правой и левой производной
        for (int k = 0; k < points.length - 1; k++) {
            yValues[k] = (points[k + 1].y - points[k].y) / (points[k + 1].x - points[k].x);
        }
        // Последнее значение - берем из предпоследнего
        yValues[points.length - 1] = yValues[points.length - 2];

        // Создаем новую табулированную функцию с полученными значениями
        return factory.create(xValues, yValues);
    }

    public synchronized TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if (!(function instanceof SynchronizedTabulatedFunction)) {
            function = new SynchronizedTabulatedFunction(function);
        }
        return ((SynchronizedTabulatedFunction) function).doSynchronously(this::derive);
    }
}
