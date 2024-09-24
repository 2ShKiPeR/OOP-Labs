package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        IdentityFunction identityFunction = new IdentityFunction();

        assertEquals(0.0, identityFunction.apply(0.0));
        assertEquals(1.0, identityFunction.apply(1.0));
        assertEquals(-1.0, identityFunction.apply(-1.0));
        assertEquals(2.5, identityFunction.apply(2.5));
        assertEquals(Double.MAX_VALUE, identityFunction.apply(Double.MAX_VALUE));
    }
}