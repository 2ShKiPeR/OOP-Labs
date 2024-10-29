package ru.ssau.tk.jabalab.lr2.concurrent;

import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable{
    private final TabulatedFunction func;
    private static final Object lock = new Object();

    public MultiplyingTask(TabulatedFunction func) {
        this.func = func;
    }

    @Override
    public void run(){
        for (int i = 0; i < func.getCount(); i++) {
            synchronized (lock) {
                func.setY(i, func.getY(i) * 2);
            }
            System.out.println("The thread " + Thread.currentThread().getName() + " has completed the task");
        }
    }

    public TabulatedFunction getFunction () {
        return func;
    }
}
