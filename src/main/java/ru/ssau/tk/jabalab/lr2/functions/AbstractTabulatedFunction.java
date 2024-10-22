package ru.ssau.tk.jabalab.lr2.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (rightX == leftX) return leftY;
        return (rightY - leftY) / (rightX - leftX) * (x - leftX) + leftY;
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) return extrapolateLeft(x);
        else if (x > rightBound()) return extrapolateRight(x);
        else if (indexOfX(x) == -1) return interpolate(x, floorIndexOfX(x));
        return getY(indexOfX(x));
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" size = ");
        sb.append(getCount());
        for (Point point : this) {
            sb.append("[").append(point.x).append("; ").append(point.y).append("]\n");
        }
        return sb.toString();

    }
}