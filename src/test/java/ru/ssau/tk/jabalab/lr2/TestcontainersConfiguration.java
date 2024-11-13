package ru.ssau.tk.jabalab.lr2;

import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
     PostgreSQLContainer<?> PostgreSQLContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("mysql:latest"));
    }

}
