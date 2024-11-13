package ru.ssau.tk.jabalab.lr2;

import org.springframework.boot.SpringApplication;
import org.testcontainers.utility.TestcontainersConfiguration;

public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.from(LR5Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}