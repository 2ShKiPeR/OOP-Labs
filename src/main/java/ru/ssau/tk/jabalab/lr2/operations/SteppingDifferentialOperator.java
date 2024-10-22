package ru.ssau.tk.jabalab.lr2.operations;

import ru.ssau.tk.jabalab.lr2.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isNaN(step) || Double.isInfinite(step))
            throw new IllegalArgumentException("Step must be finite and more than 0.0");
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }


}