package ru.ssau.tk.jabalab.lr2.functions;

import java.util.Arrays;
import java.util.function.Function;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    protected double[] arrX;
    protected double[] arrY;
    protected int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length <= 1) throw new IllegalArgumentException("xValues length must be greater than 1");
        count = xValues.length;
        arrX = Arrays.copyOf(xValues, xValues.length);
        arrY = Arrays.copyOf(yValues, yValues.length);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double tmp = xFrom;
            xFrom = xTo;
            xTo = tmp;
        }
        this.count = count;
        arrX = new double[count];
        arrY = new double[count];
        arrX[0] = xFrom;
        arrX[count - 1] = xTo;
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 1; i < count - 1; i++)
            arrX[i] = arrX[i - 1] + step;
        for (int i = 0; i < count; i++)
            arrY[i] = source.apply(arrX[i]);
    }

    @Override
    public double getY(int index) {
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
        return arrX[index];
    }


    @Override
    protected int floorIndexOfX(double x) {
        if (x < arrX[0]) return 0;
        if (x > arrX[count - 1]) return count;
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
        return interpolate(x, arrX[floorIndex], arrX[floorIndex + 1], arrY[floorIndex], arrY[floorIndex + 1]);
    }
}
