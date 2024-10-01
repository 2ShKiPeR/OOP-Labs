package ru.ssau.tk.jabalab.lr2.functions;

// Узел двусвязного списка
class Node {
    public double x;
    public double y;
    public Node next;
    public Node prev;

    Node(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

// Абстрактный класс табулированной функции
abstract class AbstractTabulatedFunction {
    protected int count;

    public abstract double leftBound();
    public abstract double rightBound();
    public abstract double getX(int index);
    public abstract double getY(int index);
    public abstract void setY(int index, double y);
    public int getCount() {
        return count;
    }
}

// Реализация табулированной функции на основе двусвязного списка
class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;

    // Конструктор с двумя массивами
    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) throw new IllegalArgumentException("Arrays must have the same length.");
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    // Конструктор с функцией и интервалом
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + i * step;
            double y = source.apply(x);
            addNode(x, y);
        }
    }

    // Приватный метод для добавления узла
    void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
        }
        count++;
    }

    // Приватный метод для получения узла по индексу
    Node getNode(int index) {
        Node current;
        if (index < count / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = head.prev;
            for (int i = count - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double y) {
        getNode(index).y = y;
    }

    public int indexOfX(double x) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.x == x) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public int indexOfY(double y) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.y == y) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public int floorIndexOfX(double x) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.x > x) {
                return (i == 0) ? -1 : i - 1;
            }
            current = current.next;
        }
        return count - 1;
    }

    // Метод интерполяции
    public double interpolate(double x) {
        if (count == 1) {
            return getY(0);
        }

        int index = floorIndexOfX(x);
        if (index == -1) {
            return leftBound(); // экстраполяция влево
        }
        if (index >= count - 1) {
            return rightBound(); // экстраполяция вправо, включая границы
        }

        Node node1 = getNode(index);
        Node node2 = getNode(index + 1);

        // Линейная интерполяция
        double x1 = node1.x;
        double x2 = node2.x;
        double y1 = node1.y;
        double y2 = node2.y;

        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }

    // Метод экстраполяции
    public double extrapolate(double x) {
        if (x < leftBound()) {
            return getY(0); // экстраполяция влево
        } else if (x > rightBound()) {
            return getY(count - 1); // экстраполяция вправо
        }
        // Если x находится в пределах, используем интерполяцию
        return interpolate(x);
    }
}