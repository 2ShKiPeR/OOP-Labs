package ru.ssau.tk.jabalab.lr2.DTO;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MathFunctionDTOTest {

    @Test
    public void testMathFunctionDTO() {
        MathFunctionDTO dto = new MathFunctionDTO(1, "Example", 2, 1.0, 10.0);

        assertEquals(1, dto.getId());
        assertEquals("Example", dto.getFunctionType());
        assertEquals(2, dto.getCount());
        assertEquals(1.0, dto.getXFrom());
        assertEquals(10.0, dto.getXTo());
    }

}