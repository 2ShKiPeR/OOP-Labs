package ru.ssau.tk.jabalab.lr2.io;

import java.io.*;
import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) throws IOException {
        try (BufferedWriter out1 = new BufferedWriter(new FileWriter("output"));
             BufferedWriter out2 = new BufferedWriter(new FileWriter("output"))) {
            ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
            LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
            FunctionsIO.writeTabulatedFunction(out1, function1);
            FunctionsIO.writeTabulatedFunction(out2, function2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
