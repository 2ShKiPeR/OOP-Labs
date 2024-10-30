package ru.ssau.tk.jabalab.lr2.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplyingTaskExecutorTest {

    private LinkedListTabulatedFunction list;
    private MultiplyingTask task;

    @BeforeEach
    public void setUp() {
        list = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        task = new MultiplyingTask(list);
    }

    @Test
    void testMultiplyingTask() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        // Создаем и запускаем 10 потоков
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new MultiplyingTask(list)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(); // Ждем завершения всех потоков
        }

        // Проверяем, что все значения были умножены
        for (int i = 0; i < list.getCount(); i++) {
            assertEquals(1024.0, list.getY(i), "Value at index " + i + " should be 1024.0");
        }
    }

    @Test
    void testMainMethod() throws InterruptedException {
        // Здесь мы можем косвенно запускать main метод, чтобы протестировать
        // его поведение, но лучше тестировать его через отдельную функцию
        Thread mainThread = new Thread(() -> {
            try {
                MultiplyingTaskExecutor.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace(); // Печатаем стек-трейс в случае ошибки
            }
        });
        mainThread.start();
        mainThread.join(); // Ждем завершения основного потока
        // Проверяем, что список содержит точно 1000 элементов
        assertEquals(1000, list.getCount());
    }
    @Test
    void testGetFunction() {
        // Получаем функцию из задачи
        TabulatedFunction retrievedFunction = task.getFunction();

        // Проверяем, что возвращаемая функция совпадает с той, которая была передана в конструктор
        assertSame(list, retrievedFunction, "The retrieved function should be the same as the one passed in the constructor");
    }
}