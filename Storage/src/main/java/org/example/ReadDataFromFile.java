package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class  ReadDataFromFile implements Readable
{
    @Override
    @Bean
    public Map<Long, UsefulObject> readData(String path)
    {
        Map<Long, UsefulObject> datas = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();

        File file = new File(path);

        try
        {
            List<UsefulObject> usefulObjects = mapper.readValue(file, new TypeReference<>() {});

            for (UsefulObject usefulObject : usefulObjects)
            {
                String name = usefulObject.getName();
                String description = usefulObject.getDescription();
                long id =  Long.parseLong(String.format("%s%d", name.hashCode(), description.length()));

                usefulObject.setId(id);

                datas.put(id, usefulObject);
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
            return  new HashMap<>();
        }


        return datas;
    }
}