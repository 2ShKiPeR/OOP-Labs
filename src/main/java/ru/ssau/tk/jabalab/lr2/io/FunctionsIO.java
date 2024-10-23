package ru.ssau.tk.jabalab.lr2.io;

import ru.ssau.tk.jabalab.lr2.functions.Point;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import java.io.*;

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
}