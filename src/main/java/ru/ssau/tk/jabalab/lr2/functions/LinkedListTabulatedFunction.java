package ru.ssau.tk.jabalab.lr2.functions;
import ru.ssau.tk.jabalab.lr2.exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {
    @Serial
    private final static long serialVersionUID = -6010119437446210523L;
    // Голова списка
    private Node head;
    // Количество узлов в списке
    private int count;

    public LinkedListTabulatedFunction() {
        head = null;
        count = 0;
    }

    // Статический вложенный класс для представления узла списка
    private static class Node implements Serializable {
        @Serial
        private final static long serialVersionUID = 478404940886376832L;
        public Node next; // Ссылка на следующий узел
        public Node prev; // Ссылка на предыдущий узел
        public double x;  // Значение x
        public double y;  // Значение y

        private Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public Node getHead() {
        return head; //     head - это поле в нашем классе
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head; // Начальная ссылка на голову списка

            @Override
            public boolean hasNext() {
                return node != null; // Проверяем, есть ли еще элементы
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the iterator.");
                }
                Point point = new Point(node.x, node.y); // Создаем точку из текущего узла
                node = (node.next != head) ? node.next : null; // Переходим к следующему элементу
                return point; // Возвращаем текущую точку
            }
        };
    }

    // Метод для добавления нового узла в конец списка
    protected void addNode(double x, double y) {
        Node newNode = new Node(x, y); // Создаем новый узел
        if (head == null) {
            // Если список пустой, новый узел становится головой
            head = newNode;
            head.next = head; // Ссылка на себя
            head.prev = head; // Ссылка на себя
        } else {
            // Если список не пустой, добавляем новый узел в конец
            Node last = head.prev; // Последний узел
            last.next = newNode; // предыдущий узел указывает на новый
            newNode.prev = last; // новый узел указывает на предыдущий
            newNode.next = head; // новый узел указывает на голову
            head.prev = newNode; // голова указывает на новый узел
        }
        count++; // Увеличиваем счетчик узлов
    }

    // Конструктор для инициализации из массивов xValues и yValues
    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        if (xValues.length != yValues.length || xValues.length < 2) {
            throw new IllegalArgumentException("Mismatched or insufficient arrays");
        }
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    // Конструктор для инициализации из функции MathFunction
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            // Меняем xFrom и xTo местами, если необходимо
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if (count < 1) {
            throw new IllegalArgumentException("Count must be at least 1");
        }

        // Вычисляем шаг для равномерной дискретизации
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + i * step; // Вычисляем текущее значение x
            double y = source.apply(x);   // Вычисляем значение y из источника
            addNode(x, y); // Добавляем узел в список
        }
    }



    @Override
    public int getCount() {
        return count; // Возвращаем количество узлов
    }

    @Override
    public double leftBound() {
        return head != null ? head.x : Double.NaN; // Возвращаем значение x головы
    }

    @Override
    public double rightBound() {
        return head != null ? head.prev.x : Double.NaN; // Возвращаем значение x последнего узла
    }

    // Метод для получения узла по индексу
    private Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node node = head;
        // Если индекс больше половины, идем от конца списка
        if (index > count / 2) {
            node = head.prev; // Начинаем с хвоста
            for (int i = count - 1; i > index; i--) {
                node = node.prev; // Двигаемся к предыдущему узлу
            }
        } else {
            // Иначе двигаемся от головы
            for (int i = 0; i < index; i++) {
                node = node.next; // Двигаемся к следующему узлу
            }
        }
        return node; // Возвращаем найденный узел
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return getNode(index).x; // Возвращаем значение x узла
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return getNode(index).y; // Возвращаем значение y узла
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        getNode(index).y = value; // Устанавливаем значение y узла
    }

    @Override
    public int indexOfX(double x) {
        // Перебираем узлы для поиска значения x
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.x == x) {
                return i; // Возвращаем индекс, если нашли
            }
            current = current.next; // Переходим к следующему узлу
        }
        return -1; // Если не нашли, возвращаем -1
    }

    @Override
    public int indexOfY(double y) {
        // Перебираем узлы для поиска значения y
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.y == y) {
                return i; // Возвращаем индекс, если нашли
            }
            current = current.next; // Переходим к следующему узлу
        }
        return -1; // Если не нашли, возвращаем -1
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("X is less than the left bound");}
        // Определяем индекс, на который будет экстраполировано значение x
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.x > x) {
                return i == 0 ? -1 : i - 1; // Возвращаем индекс или -1
            }
            current = current.next; // Переходим к следующему узлу
        }
        return count - 1; // Если x больше всех x, возвращаем последний индекс
    }

    @Override
    protected double extrapolateLeft(double x) {
        return getY(0); // Вернуть значение для постоянного экстраполирования влево
    }

    @Override
    protected double extrapolateRight(double x) {
        return getY(count - 1); // Вернуть значение для постоянного экстраполирования вправо
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return getY(0); // Вернуть единственное значение, если только один узел
        }
        Node floorNode = getNode(floorIndex); // Узел с наибольшим x
        if (x < floorNode.x || x > floorNode.next.x)
            throw new InterpolationException("x out of bounds");
        Node ceilingNode = floorIndex + 1 < count ? getNode(floorIndex + 1) : floorNode; // Узел с наименьшим x
        return interpolate(x, floorNode.x, ceilingNode.x, floorNode.y, ceilingNode.y); // Интерполяция между узлами
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node nodeToRemove = getNode(index);

        if (count == 1) {
            // Если узел единственный
            head = null;
        } else {
            // Если это не единственный узел
            Node prevNode = nodeToRemove.prev;
            Node nextNode = nodeToRemove.next;

            prevNode.next = nextNode; // Предыдущий узел указывает на следующий
            nextNode.prev = prevNode; // Следующий узел указывает на предыдущий

            // Если удаляем голову, нужно обновить голову
            if (nodeToRemove == head) {
                head = nextNode; // Обновляем голову
            }
        }

        count--; // Уменьшаем количество узлов
    }

    private void changeHead(double x, double y, Node cur) {
        Node newNode = new Node(x, y);
        newNode.next = cur;
        newNode.prev = cur.prev;
        cur.prev.next = newNode;
        cur.prev = newNode;
        head = newNode;
        count++;
    }

    @Override
    public void insert(double x, double y) {
        if (head == null) {
            addNode(x, y);
            return;
        }
        Node cur = head;
        if (cur.x > x) {
            changeHead(x, y, cur);
            return;
        } else if (cur.x == x) {
            cur.y = y;
            return;
        } else if (cur.prev.x < x) {
            addNode(x, y);
            return;
        }
        while (cur.next != head.prev) {
            if (cur.next.x > x) {
                Node newNode = new Node(x, y);
                newNode.next = cur.next;
                newNode.prev = cur;
                cur.next = newNode;
                newNode.next.prev = newNode;
                count++;
                return;
            } else if (cur.next.x == x) {
                cur.next.y = y;
                return;
            }
            cur = cur.next;
        }
    }
}

