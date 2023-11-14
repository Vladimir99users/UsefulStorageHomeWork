package org.example;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner
{
    @Value("${input.data.path}")
    private String inputDataPath;
    private DisplayService displayService;

    @Override
    public void run(String... args)
    {
        initializeComponent();
        displayService.runService();
    }


    private void initializeComponent()
    {
        //Инициализация всех компонентов.
        Requestable requestable = new Requests();

        Map<Long,UsefulObject> objectMap = getObjectMapFromFile();

        displayService = new DisplayService(new RequestService(requestable, objectMap));
    }


    private Map<Long,UsefulObject> getObjectMapFromFile()
    {
        //получаем нужные данные из файла.
        try
        {
            Readable readable = new ReadDataFromFile();

            return readable.readData(inputDataPath);
        } catch (IOException exeption)
        {
            throw new RuntimeException(exeption);
        }
    }

}
