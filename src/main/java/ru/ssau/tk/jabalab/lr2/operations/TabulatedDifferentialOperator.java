package ru.ssau.tk.jabalab.lr2.operations;

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
            xValues[i] = points[i].getX();
        }

        // Вычисляем значения y с использованием правой и левой производной
        for (int k = 0; k < points.length - 1; k++) {
            yValues[k] = (points[k + 1].getY() - points[k].getY()) / (points[k + 1].getX() - points[k].getX());
        }
        // Последнее значение - берем из предпоследнего
        yValues[points.length - 1] = yValues[points.length - 2];

        // Создаем новую табулированную функцию с полученными значениями
        return factory.create(xValues, yValues);
    }
}
