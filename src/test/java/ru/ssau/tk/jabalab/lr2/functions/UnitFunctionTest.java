package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitFunctionTest {
    @Test
    public void testApply() {
        UnitFunction unitFunction = new UnitFunction();
        assertEquals(1.0, unitFunction.apply(19));
    }
}
