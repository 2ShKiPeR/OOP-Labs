package ru.ssau.tk.jabalab.lr2.functions;

class ChaplyginMethod implements MathFunction {
    private final double L;  // Длина области
    private final double T;  // Время
    private final int Nx;    // Количество точек по пространству
    private final int Nt;    // Количество временных шагов
    private final double dx;  // Шаг по пространству
    private final double dt;  // Шаг по времени
    private double[] u;  // Значения функции
    private final double[] x;  // Координаты

    public ChaplyginMethod(double L, double T, int Nx, int Nt) {
        this.L = L;
        this.T = T;
        this.Nx = Nx;
        this.Nt = Nt;
        this.dx = L / (Nx - 1);
        this.dt = T / Nt;
        this.u = new double[Nx];
        this.x = new double[Nx];

        for (int i = 0; i < Nx; i++) {
            x[i] = i * dx;
        }

        // Установите начальные условия здесь, например:
        initializeCondition();
    }

    private void initializeCondition() {
        for (int i = 0; i < Nx; i++) {
            u[i] = Math.exp(-100 * Math.pow(x[i] - 0.5, 2)); // Гауссовская функция
        }
    }

    @Override
    public double apply(double xPosition) {
        // Выполнить расчет по методу Чаплыгина для заданной позиции xPosition
        int index = (int) (xPosition / dx);
        if (index < 1 || index >= Nx - 1) {
            throw new IllegalArgumentException("xPosition вне диапазона");
        }

        for (int n = 1; n < Nt; n++) {
            double[] uNew = u.clone();
            for (int i = 1; i < Nx - 1; i++) {
                uNew[i] = u[i] - dt / (2 * dx) * (u[i + 1] - u[i - 1]);
            }
            u = uNew;
        }

        return u[index];
    }

    public double getL() {
        return L;
    }

    public double getT() {
        return T;
    }
    public int getNx() {
        return Nx;
    }

    public double getDx() {
        return dx;
    }
}
