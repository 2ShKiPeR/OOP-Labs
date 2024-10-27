/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package ru.ssau.tk.jabalab.lr2.concurrent;

import ru.ssau.tk.jabalab.lr2.functions.Point;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction func;

    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.func = function;
    }

    @Override
    public int getCount() {
        synchronized (func) {
            return func.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (func) {
            return func.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (func) {
            return func.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (func) {
            func.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (func) {
            return func.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (func) {
            return func.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (func) {
            return func.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (func) {
            return func.rightBound();
        }
    }

    @Override
    public double apply(double x) {
        synchronized (func) {
            return func.apply(x);
        }
    }
    @Override
    public Iterator<Point> iterator() {
        synchronized (func) {
            Point[] points = TabulatedFunctionOperationService.asPoints(func);
            return new Iterator<Point>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < func.getCount();
                }

                @Override
                public Point next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    Point point = points[i];
                    i++;
                    return point;
                }
            };
        }
    }
}
