package ru.ssau.tk.jabalab.lr2.functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulateFunctionTest {

    @Test
    void checkLengthIsTheSameTest1() {
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(new double[]{1, 2, 3}, new double[]{4, 5}));
    }

    @Test
    void checkLengthIsTheSameTest2() {
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(new double[]{1, 2}, new double[]{4, 5, 6}));
    }

    @Test
    void checkLengthIsTheSameTest3() {
        assertDoesNotThrow(() -> AbstractTabulatedFunction.checkLengthIsTheSame(new double[]{1, 2, 3}, new double[]{4, 5, 6}));
    }

    @Test
    void checkSorted(){
        assertDoesNotThrow(() -> AbstractTabulatedFunction.checkSorted(new double[]{1, 2, 3}));
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(new double[]{5, 4, 6}));
    }
}