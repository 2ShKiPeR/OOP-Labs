package ru.ssau.tk.jabalab.lr2.io;

import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        // Чтение из файла
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            ArrayTabulatedFunction functionFromFile = (ArrayTabulatedFunction) FunctionsIO.readTabulatedFunction(inputStream, arrayFactory);
            System.out.println("Function read from file: " + functionFromFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Чтение из консоли
        System.out.println("Введите размер и значения функции:");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = consoleReader.readLine();
            String[] parts = line.split(" ");
            int size = Integer.parseInt(parts[0]);
            double[] xValues = new double[size];
            double[] yValues = new double[size];

            for (int i = 0; i < size; i++) {
                xValues[i] = Double.parseDouble(parts[1 + 2 * i]);
                yValues[i] = Double.parseDouble(parts[2 + 2 * i]);
            }

            TabulatedFunctionFactory linkedFactory = new LinkedListTabulatedFunctionFactory();
            LinkedListTabulatedFunction functionFromConsole = (LinkedListTabulatedFunction) linkedFactory.create(xValues, yValues);
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
            TabulatedFunction derivative = operator.derive(functionFromConsole);
            System.out.println("Derivative of the function: " + derivative.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Invalid input format. Please enter numbers.");
        }
    }
}