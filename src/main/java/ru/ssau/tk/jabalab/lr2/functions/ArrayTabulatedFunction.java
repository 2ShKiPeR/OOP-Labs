package ru.ssau.tk.jabalab.lr2.functions;

import ru.ssau.tk.jabalab.lr2.exceptions.InterpolationException;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.io.Serializable;
import java.io.Serial;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    protected double[] arrX;
    protected double[] arrY;
    protected int count;
    private static final int INITIAL_CAPACITY = 2; // Начальный размер массива
    private static final double EXPANSION_FACTOR = 1.5; // Коэффициент расширения массива

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        if (xValues.length <= 1) throw new IllegalArgumentException("Length of xValues must be greater than 1");
        checkSorted(xValues);
        count = xValues.length;
        arrX = new double[Math.max(INITIAL_CAPACITY, xValues.length)];
        arrY = new double[arrX.length];
        System.arraycopy(xValues, 0, arrX, 0, count);
        System.arraycopy(yValues, 0, arrY, 0, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) throw new IllegalArgumentException("Count must be at least 2");
        if (xFrom > xTo) {
            double tmp = xFrom;
            xFrom = xTo;
            xTo = tmp;
        }
        this.count = count;
        arrX = new double[Math.max(INITIAL_CAPACITY, count)];
        arrY = new double[arrX.length];
        arrX[0] = xFrom;
        arrX[count - 1] = xTo;
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 1; i < count - 1; i++)
            arrX[i] = arrX[i - 1] + step;
        for (int i = 0; i < count; i++)
            arrY[i] = source.apply(arrX[i]);
    }
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) throw new NoSuchElementException();
                Point point = new Point(arrX[i], arrY[i]);
                i++;
                return point;
            }
        };
    }

    @Override
    public void insert(double x, double y) {
        int index = indexOfX(x);

        if (index != -1) {
            arrY[index] = y; // Заменяем существующее значение
            return;
        }

        // Создаем новый массив, если нет места для вставки
        if (count >= arrX.length) {
            int newLength = (int) (arrX.length * EXPANSION_FACTOR);
            arrX = Arrays.copyOf(arrX, newLength);
            arrY = Arrays.copyOf(arrY, newLength);
        }

        // Поиск позиции для вставки
        int insertIndex = 0;
        while (insertIndex < count && arrX[insertIndex] < x) {
            insertIndex++;
        }

        // Сдвигаем элементы
        System.arraycopy(arrX, insertIndex, arrX, insertIndex + 1, count - insertIndex);
        System.arraycopy(arrY, insertIndex, arrY, insertIndex + 1, count - insertIndex);

        // Вставляем новое значение
        arrX[insertIndex] = x;
        arrY[insertIndex] = y;
        count++;
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) throw new IndexOutOfBoundsException("Invalid index: " + index);
        return arrY[index];
    }

    @Override
    public void setY(int index, double value) {
        arrY[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++)
            if (arrX[i] == x) return i;
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++)
            if (arrY[i] == y) return i;
        return -1;
    }

    @Override
    public double leftBound() {
        return arrX[0];
    }

    @Override
    public double rightBound() {
        return arrX[count - 1];
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) throw new IndexOutOfBoundsException("Invalid index: " + index);
        return arrX[index];
    }


    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound() || x > rightBound()) throw new IllegalArgumentException("x is out of bounds");
        if (indexOfX(x) != -1) return indexOfX(x);
        int index = 0;
        for (int i = 1; i < count - 1; i++) {
            if (x < arrX[i]) {
                index = i - 1;
                break;
            }
        }
        return index;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, arrX[0], arrX[1], arrY[0], arrY[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, arrX[count - 2], arrX[count - 1], arrY[count - 2], arrY[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < arrX[floorIndex] || x > arrX[floorIndex + 1])
            throw new InterpolationException("x is out of bounds");
        return interpolate(x, arrX[floorIndex], arrX[floorIndex + 1], arrY[floorIndex], arrY[floorIndex + 1]);
    }
}