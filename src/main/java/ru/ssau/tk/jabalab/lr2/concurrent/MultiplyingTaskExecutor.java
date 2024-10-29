package ru.ssau.tk.jabalab.lr2.concurrent;

import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws Exception {
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++)
            threads.add(new Thread(new MultiplyingTask(list)));
        for (Thread thread : threads)
            thread.start();
        while (!threads.isEmpty())
            threads.removeIf(thread -> !thread.isAlive());
        System.out.println(list);
    }
}
