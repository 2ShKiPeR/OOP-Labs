package ru.ssau.tk.jabalab.lr2.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointDTOTest {

    @Test
    public void testPointDTO() {
        PointDTO dto = new PointDTO(1, 1, 1.0, 10.0);

        assertEquals(1, dto.getId());
        assertEquals(1, dto.getFunctionId());
        assertEquals(1.0, dto.getXVal());
        assertEquals(10.0, dto.getYVal());
    }

}