package ru.ssau.tk.jabalab.lr2.io;

import java.io.*;
import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try (
                BufferedOutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream("output/array_function.bin"));
                BufferedOutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream("output/binary function.bin"))
        ) {
            ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
            LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
            FunctionsIO.writeTabulatedFunction(outputStream1, function1);
            FunctionsIO.writeTabulatedFunction(outputStream2, function2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}