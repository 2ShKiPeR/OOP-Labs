package ru.ssau.tk.jabalab.lr2.io;

import ru.ssau.tk.jabalab.lr2.functions.Point;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.factory.TabulatedFunctionFactory;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException("Functions cannot be instantiated");
    }

    static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter out = new PrintWriter(writer);
        out.println(function.getCount());
        for (Point point : function) {
            out.printf("%f %f\n", point.x, point.y);
            out.flush();
        }
    }

    static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int size = Integer.parseInt(reader.readLine());
        double[] xValues = new double[size];
        double[] yValues = new double[size];
        NumberFormat formatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < size; i++) {
            String line = reader.readLine();
            String[] values = line.split(" ");
            try {
                xValues[i] = formatter.parse(values[0]).doubleValue();
                yValues[i] = formatter.parse(values[1]).doubleValue();
            } catch (ParseException e) {
                throw new IOException(e);
            }
        }
        return factory.create(xValues, yValues);
    }

    static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(function);
        out.flush();
    }

    static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        var inp = new ObjectInputStream(stream);
        return (TabulatedFunction) inp.readObject();
    }
}