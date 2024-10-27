/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package ru.ssau.tk.jabalab.lr2.concurrent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.ssau.tk.jabalab.lr2.concurrent.ReadWriteTaskExecutor;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.ConstantFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadWriteTaskExecutorTest {

    @Test
    void testFunctionInitialization() {
        // Создаем табулированную функцию с помощью параметров
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);

        // Проверяем, что функция инициализировалась корректно
        assertEquals(1000, function.getCount());
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(-1.0, function.getY(i)); // Проверяем, что все значения равны -1
        }
    }

    @Test
    void testReadAndWriteTasksExecution() throws InterruptedException {
        // Создаем табулированную функцию
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);

        // Создаем потоки
        Thread thrReadTask = new Thread(new ReadTask(function));
        Thread thrWriteTask = new Thread(new WriteTask(function, 0.5));

        // Запускаем потоки
        thrWriteTask.start();
        thrReadTask.start();

        // Ждем завершения потоков
        thrWriteTask.join();
        thrReadTask.join();

        // Проверяем, что все значения были записаны
        for (int i = 0; i < function.getCount(); i++) {
            // Каждая запись в WriteTask должна была перекрыть -1 на 0.5
            assertEquals(0.5, function.getY(i));
        }
    }
    @Test
    void testMainExecution() throws InterruptedException {
        // Ожидаем завершения работы метода main
        Thread mainThread = new Thread(() -> {
            ReadWriteTaskExecutor.main(new String[0]);
        });

        // Запускаем поток для выполнения main
        mainThread.start();

        // Должны подождать немного, чтобы дать время потокам завершиться
        mainThread.join(1000);  // задаём таймаут на 1 секунду, чтобы потоки могли завершиться

        // Создаём экземпляр функции, чтобы проверить итоговые значения
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);

        // Проверяем, что все значения в функции изменены на -1
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(-1, function.getY(i)); // Проверяем, что все значения равны -1
        }
    }
}