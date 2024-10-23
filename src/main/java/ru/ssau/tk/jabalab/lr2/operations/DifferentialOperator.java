package ru.ssau.tk.jabalab.lr2.operations;
import ru.ssau.tk.jabalab.lr2.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}
