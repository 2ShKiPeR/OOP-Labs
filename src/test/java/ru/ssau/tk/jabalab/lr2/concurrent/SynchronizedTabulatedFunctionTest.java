package ru.ssau.tk.jabalab.lr2.concurrent;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.Point;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SynchronizedTabulatedFunctionTest {

    @Test
    void testGetCount() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2}, new double[]{1, 4});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(2, synchronizedFunction.getCount());
    }

    @Test
    void testGetX() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(1, synchronizedFunction.getX(0));
        assertEquals(2, synchronizedFunction.getX(1));
        assertEquals(3, synchronizedFunction.getX(2));
    }

    @Test
    void testGetY() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(1, synchronizedFunction.getY(0));
        assertEquals(4, synchronizedFunction.getY(1));
        assertEquals(9, synchronizedFunction.getY(2));
    }

    @Test
    void testSetY() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        synchronizedFunction.setY(1, 5);
        assertEquals(5, synchronizedFunction.getY(1));
    }
    @Test
    void testIndexOfX() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(0, synchronizedFunction.indexOfX(1));
        assertEquals(1, synchronizedFunction.indexOfX(2));
        assertEquals(2, synchronizedFunction.indexOfX(3));
        assertEquals(-1, synchronizedFunction.indexOfX(4)); // Не найдено
    }

    @Test
    void testIndexOfY() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(0, synchronizedFunction.indexOfY(1));
        assertEquals(1, synchronizedFunction.indexOfY(4));
        assertEquals(2, synchronizedFunction.indexOfY(9));
        assertEquals(-1, synchronizedFunction.indexOfY(5)); // Не найдено
    }

    @Test
    void testLeftBound() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(1.0, synchronizedFunction.leftBound());
    }

    @Test
    void testRightBound() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(3.0, synchronizedFunction.rightBound());
    }

    @Test
    void testApply() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(1.0, synchronizedFunction.apply(1.0));
        assertEquals(4.0, synchronizedFunction.apply(2.0));
        assertEquals(9.0, synchronizedFunction.apply(3.0));
    }
    @Test
    void testIterator() {
        TabulatedFunction originalFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
        SynchronizedTabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);

        assertEquals(3, synchronizedFunction.getCount());

        Iterator<Point> iterator = synchronizedFunction.iterator();

        assertTrue(iterator.hasNext());
        Point point1 = iterator.next();
        assertEquals(1.0, point1.x);
        assertEquals(1.0, point1.y);

        assertTrue(iterator.hasNext());
        Point point2 = iterator.next();
        assertEquals(2.0, point2.x);
        assertEquals(4.0, point2.y);

        assertTrue(iterator.hasNext());
        Point point3 = iterator.next();
        assertEquals(3.0, point3.x);
        assertEquals(9.0, point3.y);

        assertFalse(iterator.hasNext());
    }
}