package ru.ssau.tk.jabalab.lr2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "math_function")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MathFunctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    @NotNull
    private String functionType;

    @Column(name = "count")
    private int count;

    @Column(name = "x_from")
    private Double xFrom;

    @Column(name = "x_to")
    private Double xTo;

    @OneToMany(mappedBy = "functionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointEntity> points;

}