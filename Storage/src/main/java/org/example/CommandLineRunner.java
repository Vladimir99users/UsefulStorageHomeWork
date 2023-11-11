package org.example;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner
{
    @Value("${input.data.path}")
    private String inputDataPath;
    private DisplayService displayService;

    @Override
    public void run(String... args) throws Exception
    {
        initializeComponent();
        displayService.runService();
    }
    @SneakyThrows
    private void initializeComponent()
    {
        Requestable requestable = new Requests();

        Readable readable = new ReadDataFromFile();
        Map<Long,UsefulObject> objectMap = readable.readData(inputDataPath);

        displayService = new DisplayService(new RequestService(requestable, objectMap));
    }

}
