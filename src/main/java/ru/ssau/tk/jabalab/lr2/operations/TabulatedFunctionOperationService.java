package ru.ssau.tk.jabalab.lr2.operations;

import ru.ssau.tk.jabalab.lr2.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.Point;
import ru.ssau.tk.jabalab.lr2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    TabulatedFunctionFactory factory;

    TabulatedFunctionOperationService() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int count = tabulatedFunction.getCount(); // Получаем количество точек
        Point[] points = new Point[count]; // Инициализируем массив точек

        // Пробегаем по всем точкам
        for (int i = 0; i < count; i++) {
            double x = tabulatedFunction.getX(i); // Получаем значение x
            double y = tabulatedFunction.getY(i); // Получаем значение y
            points[i] = new Point(x, y); // Создаем новую точку и добавляем её в массив
        }

        return points; // Возвращаем массив точек
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount())
            throw new InconsistentFunctionsException("Different size of TabulatedFunctions");
        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);
        double[] xValues = new double[pointsA.length];
        double[] yValues = new double[pointsA.length];
        for (int i = 0; i < pointsA.length; i++) {
            if (pointsA[i].x != pointsB[i].x) throw new InconsistentFunctionsException("X coordinates do not match");
            xValues[i] = pointsA[i].x;
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction addition(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, Double::sum);
    }

    public TabulatedFunction subtraction(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (x, y) -> x - y);
    }

    public TabulatedFunction division(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (x, y) -> x / y);
    }

    public TabulatedFunction multiplication(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (x, y) -> x * y);
    }

    private interface BiOperation {
        double apply(double x, double y);
    }
}
