package ru.ssau.tk.jabalab.lr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.tk.jabalab.lr2.entity.MathFunctionEntity;

import java.util.List;

@Repository
public interface MathFunctionRepository extends JpaRepository<MathFunctionEntity, Integer> {
    List<MathFunctionEntity> findByFunctionType(String functionType);
}