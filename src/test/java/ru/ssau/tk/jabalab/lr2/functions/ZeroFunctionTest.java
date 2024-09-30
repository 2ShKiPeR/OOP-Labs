package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZeroFunctionTest {
    @Test
    public void testApply() {
        ZeroFunction zeroFunction1 = new ZeroFunction();
        assertEquals(0.0, zeroFunction1.apply(15));
    }
}
