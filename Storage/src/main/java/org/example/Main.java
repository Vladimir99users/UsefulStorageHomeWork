package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
@SpringBootApplication
public class Main
{
    @Autowired
    private RequestService requestService;

    @Autowired
    private  DisplayService displayService;

    @Value("${input.data.path}")
    private String inputDataPath;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args).close();
    }


    @Bean
    public CommandLineRunner run() {

        return args -> {
            System.out.println("Hello world!");
            requestService.readJsonFile(inputDataPath);
            displayService.runService();
        };
    }




}