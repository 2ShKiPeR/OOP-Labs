package ru.ssau.tk.jabalab.lr2.concurrent;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;

public class IntegrationTest {
    @Test
    void test() throws InterruptedException {
        Integration integrate = new Integration(new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{6, 7, 8, 9, 10}));
        System.out.println(integrate.ParabolasMethod());
    }

}
