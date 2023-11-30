package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner
{
    @Value("${input.data.path}")
    private String inputDataPath;
    private final DisplayService displayService;
    private final RequestService requestService;
    private final Readable readable;

    public CommandLineRunner(DisplayService displayService,RequestService requestService, Readable readable )
    {
        this.displayService = displayService;
        this.requestService = requestService;
        this.readable = readable;
    }

    @Override
    public void run(String... args)
    {
        Map<Long,UsefulObject> objectMap = getObjectMapFromFile();
        requestService.addAllUsefulObject(objectMap);
        //displayService.runService();
    }

    private Map<Long,UsefulObject> getObjectMapFromFile()
    {
        //получаем нужные данные из файла.
        return readable.readData(inputDataPath);
    }

}
