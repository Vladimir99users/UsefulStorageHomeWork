package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class CommandLiner implements CommandLineRunner
{
    private final String inputDataPath;
    private DisplayService displayService;
    public void Initialize() throws IOException
    {
        Requestable requestable = new Requests();

        Readable readable = new ReadDataFromFile();
        Map<Long,UsefulObject> objectMap = readable.readData(inputDataPath);

        displayService = new DisplayService(new RequestService(requestable, objectMap));
    }

    @Bean
    @Override
    public void run(String... args) throws Exception
    {
        displayService.runService();
    }
}
