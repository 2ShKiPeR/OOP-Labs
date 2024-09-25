package ru.ssau.tk.jabalab.lr2.functions;

public class CompositeFunction implements MathFunction {
    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    @Override
    public double apply(double x) {
        double firstResult = firstFunction.apply(x);
        return secondFunction.apply(firstResult);
    }
}
