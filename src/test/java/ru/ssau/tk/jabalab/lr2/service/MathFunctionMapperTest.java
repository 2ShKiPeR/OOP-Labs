package ru.ssau.tk.jabalab.lr2.service;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.model.MathFunctionEntity;
import ru.ssau.tk.jabalab.lr2.services.MathFunctionMapper;
import ru.ssau.tk.jabalab.lr2.DTO.MathFunctionDTO;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionMapperTest {

    @Test
    void testFunctionEntityToDTO() {
        MathFunctionEntity entity = new MathFunctionEntity(1, "Example", 10, 0.0, 10.0, null);
        MathFunctionDTO dto = MathFunctionMapper.functionEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getFunctionType(), dto.getFunctionType());

        dto = null;
        assertNull(dto);
    }

    @Test
    void testFunctionDTOToFunctionEntity() {
        MathFunctionDTO dto = new MathFunctionDTO(1, "Example", 10, 0.0, 10.0);
        MathFunctionEntity entity = MathFunctionMapper.functionDTOToFunctionEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getFunctionType(), entity.getFunctionType());

        entity = null;
        assertNull(entity);
    }

}