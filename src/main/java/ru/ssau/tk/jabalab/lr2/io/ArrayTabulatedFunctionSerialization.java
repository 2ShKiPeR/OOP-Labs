package ru.ssau.tk.jabalab.lr2.io;

import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))) {
            ArrayTabulatedFunction arr = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            TabulatedFunction first = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory()).derive(arr);
            TabulatedFunction second = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory()).derive(first);
            FunctionsIO.serialize(bos, arr);
            FunctionsIO.serialize(bos, first);
            FunctionsIO.serialize(bos, second);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream bos = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(bos));
            System.out.println(FunctionsIO.deserialize(bos));
            System.out.println(FunctionsIO.deserialize(bos));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
