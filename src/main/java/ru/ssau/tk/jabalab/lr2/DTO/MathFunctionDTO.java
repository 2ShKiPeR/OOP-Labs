package ru.ssau.tk.jabalab.lr2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MathFunctionDTO {
    private int id;
    private String functionType;
    private int count;
    private double xFrom;
    private double xTo;
}