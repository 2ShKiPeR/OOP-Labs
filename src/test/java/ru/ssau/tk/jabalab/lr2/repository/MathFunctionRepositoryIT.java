package ru.ssau.tk.jabalab.lr2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.jabalab.lr2.model.MathFunctionEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = {"/sql/schema.sql", "/sql/math_functions.sql"})
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MathFunctionRepositoryIT {

    @Autowired
    private MathFunctionRepository mathFunctionRepository;

    @BeforeEach
    public void setup() {
        MathFunctionEntity function = new MathFunctionEntity();
        function.setFunctionType("Example");
        this.mathFunctionRepository.save(function);
    }

    @Test
    public void testfindByFunctionName_ReturnsFunctionList() {
        List<MathFunctionEntity> functions = this.mathFunctionRepository.findByFunctionType("Example");

        assertEquals(1, functions.size());
        assertEquals("Example", functions.get(0).getFunctionType());
    }

}