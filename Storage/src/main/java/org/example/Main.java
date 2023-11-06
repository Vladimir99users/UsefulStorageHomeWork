package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@SpringBootApplication
public class Main
{
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args).close();
    }

    @Value("${input.data.path}")
    private String inputDataPath;
    @Bean
    public CommandLineRunner run() {
        return args -> {

            CommandLiner liner = new CommandLiner(inputDataPath);
            liner.Initialize();
            liner.run(args);
        };
    }
}