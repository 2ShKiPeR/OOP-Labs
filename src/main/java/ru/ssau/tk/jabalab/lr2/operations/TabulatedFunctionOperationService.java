package ru.ssau.tk.jabalab.lr2.operations;

import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.Point;

public class TabulatedFunctionOperationService {

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
}
