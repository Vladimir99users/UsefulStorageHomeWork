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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class  ReadDataFromFile implements Readable
{
    @Override
    public Map<Long, UsefulObject> readData(String path)
    {
        Map<Long, UsefulObject> datas = new HashMap<>();

        File file = new File(path);

        List<UsefulObject> usefulObjects = getListValue(file);


        for (UsefulObject usefulObject : usefulObjects)
        {
            String name = usefulObject.getName();
            String description = usefulObject.getDescription();

            long id =  usefulObject.hashCode();

            usefulObject.setId(id);

            datas.put(id, usefulObject);
        }


        return datas;
    }

    private List<UsefulObject> getListValue(File file)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            return mapper.readValue(file, new TypeReference<>() {});
        }
        catch(IOException e)
        {
            throw new RuntimeException("An error occurred while reading file: " + file.toString());
        }
    }
}