package ru.ssau.tk.jabalab.lr2.service;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.DTO.PointDTO;
import ru.ssau.tk.jabalab.lr2.entity.PointEntity;
import ru.ssau.tk.jabalab.lr2.services.PointMapper;

import static org.junit.jupiter.api.Assertions.*;

class PointMapperTest {

    @Test
    void testPointEntityToDTO() {
        PointEntity entity = new PointEntity(1, null, 1.0, 2.0);
        PointDTO dto = PointMapper.pointEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getXVal(), dto.getXVal());

        dto = null;
        assertNull(dto);
    }

    @Test
    void testPointDTOToPointEntity() {
        PointDTO dto = new PointDTO(1, 1, 1.0, 2.0);
        PointEntity entity = PointMapper.pointDTOToPointEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getXVal(), entity.getXVal());

        entity = null;
        assertNull(entity);
    }
}
