package ru.ssau.tk.jabalab.lr2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.jabalab.lr2.model.MathFunctionEntity;
import ru.ssau.tk.jabalab.lr2.model.PointEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = {"/sql/schema.sql", "/sql/points.sql"})
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PointRepositoryIT {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private MathFunctionRepository mathFunctionRepository;

    @BeforeEach
    public void setup() {
        MathFunctionEntity functionEntity = new MathFunctionEntity();
        functionEntity.setId(1);
        functionEntity.setFunctionType("example_function");
        functionEntity.setCount(10);
        functionEntity.setXFrom(0D);
        functionEntity.setXTo(10D);
        mathFunctionRepository.save(functionEntity);

        PointEntity pointEntity = new PointEntity();
        pointEntity.setId(1);
        pointEntity.setFunctionEntity(functionEntity);
        pointEntity.setXVal(0D);
        pointEntity.setYVal(0D);
        pointRepository.save(pointEntity);
    }

    @Test
    public void testFindByFunctionEntity_ReturnsFilteredPointsList() {
        MathFunctionEntity functionEntity = mathFunctionRepository.findById(1).orElseThrow();

        List<PointEntity> points = pointRepository.findByFunctionEntity(functionEntity);

        assertEquals(1, points.size());
        assertEquals(1, points.get(0).getId());
    }
}