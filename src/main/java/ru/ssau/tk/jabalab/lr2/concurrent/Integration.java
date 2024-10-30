/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package ru.ssau.tk.jabalab.lr2.concurrent;

import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;

public class Integration {
    private final TabulatedFunction func;
    private volatile double integral;

    public Integration(TabulatedFunction func) {
        this.func = func;
        integral = 0;
    }

    double ParabolasMethod() throws InterruptedException {
        double h = (func.rightBound() - func.leftBound()) / func.getCount();
        Runnable task1 = () -> {
            for (int i = 1; i < func.getCount() - 1; i += 2) {
                synchronized (func) {
                    integral += func.getY(i - 1);
                }
            }
        };
        Runnable task2 = () -> {
            for (int i = 1; i < func.getCount() - 1; i += 2) {
                synchronized (func) {
                    integral += func.getY(i) * 4;
                }
            }
        };

        Runnable task3 = () -> {
            for (int i = 1; i < func.getCount() - 1; i += 2) {
                synchronized (func) {
                    integral += func.getY(i + 1);
                }
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (func) {
            integral = integral * h / 3;
        }
        return integral;

    }
}
