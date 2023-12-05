package org.example;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info
                (
                        title = "Hello World"
                )
)
@Hidden
public class Main
{
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}