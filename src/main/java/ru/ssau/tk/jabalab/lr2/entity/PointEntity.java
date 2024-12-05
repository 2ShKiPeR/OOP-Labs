package ru.ssau.tk.jabalab.lr2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "function_id", nullable = false)
    private MathFunctionEntity functionEntity;

    @Column(name = "x_val")
    private Double xVal;

    @Column(name = "y_val")
    private Double yVal;

}