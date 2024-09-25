package ru.ssau.tk.jabalab.lr2.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChaplyginFunctionTest {

    private final ChaplyginFunction chaplyginFunction = new ChaplyginFunction();

    @Test
    public void testSingleValue() {
        double input = Math.PI / 2;
        double expectedOutput = Math.sin(input);
        assertEquals(expectedOutput, chaplyginFunction.apply(input), 1e-10);
    }

    @Test
    public void testMultipleValues() {
        double[] inputs = {0, Math.PI / 2, Math.PI, 3 * Math.PI / 2};
        double[] expectedOutputs = {0, 1, 0, -1};

        double[] results = chaplyginFunction.apply(inputs);

        assertArrayEquals(expectedOutputs, results, 1e-10);
    }

    @Test
    public void testNegativeValues() {
        double input = -Math.PI / 2;
        double expectedOutput = Math.sin(input);
        assertEquals(expectedOutput, chaplyginFunction.apply(input), 1e-10);
    }

    @Test
    public void testZero() {
        double input = 0;
        double expectedOutput = Math.sin(input);
        assertEquals(expectedOutput, chaplyginFunction.apply(input), 1e-10);
    }
}