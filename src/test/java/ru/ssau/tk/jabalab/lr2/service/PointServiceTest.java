package ru.ssau.tk.jabalab.lr2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ssau.tk.jabalab.lr2.DTO.PointDTO;
import ru.ssau.tk.jabalab.lr2.entity.MathFunctionEntity;
import ru.ssau.tk.jabalab.lr2.entity.PointEntity;
import ru.ssau.tk.jabalab.lr2.repository.PointRepository;
import ru.ssau.tk.jabalab.lr2.repository.MathFunctionRepository;
import ru.ssau.tk.jabalab.lr2.services.PointService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PointServiceTest {

    @Mock
    private PointRepository pointRepository;

    @Mock
    private MathFunctionRepository mathFunctionRepository;

    @InjectMocks
    private PointService pointService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByFunctionEntity() {
        MathFunctionEntity function = new MathFunctionEntity(1, "linear", 10, 0.0, 10.0, null);
        PointEntity point = new PointEntity(1, function, 1.0, 2.0);
        when(mathFunctionRepository.findById(1)).thenReturn(Optional.of(function));
        when(pointRepository.findByFunctionEntity(function)).thenReturn(Arrays.asList(point));

        List<PointDTO> points = pointService.findAllPoints(1);

        assertNotNull(points);
        assertEquals(1, points.size());
        assertEquals(point.getXVal(), points.get(0).getXVal());
    }

    @Test
    void testCreate() {
        PointDTO dto = new PointDTO(1, 1, 1.0, 2.0);
        PointEntity entity = new PointEntity(1, null, 1.0, 2.0);
        when(pointRepository.save(any())).thenReturn(entity);

        PointDTO createdDto = pointService.create(dto);

        assertNotNull(createdDto);
        assertEquals(dto.getXVal(), createdDto.getXVal());
        verify(pointRepository).save(any());
    }

    @Test
    void testRead() {
        PointEntity entity = new PointEntity(1, null, 1.0, 2.0);
        when(pointRepository.findById(1)).thenReturn(Optional.of(entity));

        PointDTO dto = pointService.read(1);

        assertNotNull(dto);
        assertEquals(entity.getXVal(), dto.getXVal());
    }

    @Test
    void testUpdate() {
        PointDTO dto = new PointDTO(1, 1, 1.0, 2.0);
        PointEntity entity = new PointEntity(1, null, 1.0, 2.0);
        when(pointRepository.save(any())).thenReturn(entity);

        PointDTO updatedDto = pointService.update(dto);

        assertNotNull(updatedDto);
        assertEquals(dto.getXVal(), updatedDto.getXVal());
        verify(pointRepository).save(any());
    }

    @Test
    void testDelete() {
        pointService.delete(1);
        verify(pointRepository).deleteById(1);
    }
}