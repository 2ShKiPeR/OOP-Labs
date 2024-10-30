package ru.ssau.tk.jabalab.lr2.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.functions.CompositeFunction;
import ru.ssau.tk.jabalab.lr2.functions.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathFunctionTest {

    @Test
    void andThen() {
        // Создаем тестовые функции
        MathFunction function1 = x -> x * 2;
        MathFunction function2 = x -> x + 1;

        // Вызываем метод andThen()
        CompositeFunction compositeFunction = function1.andThen(function2);

        // Проверяем, что композиция работает корректно
        double result = compositeFunction.apply(2.0);
        assertEquals(5.0, result, 0.001); // (2 * 2) + 1 = 5
    }
}
