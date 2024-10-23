package ru.ssau.tk.jabalab.lr2.functions;

import ru.ssau.tk.jabalab.lr2.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.jabalab.lr2.exceptions.ArrayIsNotSortedException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (rightX == leftX) return leftY;
        return (rightY - leftY) / (rightX - leftX) * (x - leftX) + leftY;
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) return extrapolateLeft(x);
        else if (x > rightBound()) return extrapolateRight(x);
        else if (indexOfX(x) == -1) return interpolate(x, floorIndexOfX(x));
        return getY(indexOfX(x));
    }

    static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) throw new DifferentLengthOfArraysException("different length of arrays");
    }

    static void checkSorted(double[] xValues) {
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1]) throw new ArrayIsNotSortedException("array isn't sorted");
        }
    }
}