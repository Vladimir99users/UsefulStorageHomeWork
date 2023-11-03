package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@Component
public  class  ReadDataFromFile implements Readable
{


    @Override
    public Map<Long, UsefulObject> readData(String path) {
        Map<Long, UsefulObject> datas = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(path);

        File file = new File(path);
        JsonNode nodes = null;

        try
        {
            nodes = mapper.readTree(file);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }


        for(JsonNode node : nodes)
        {
            long id = 0;
            String name = node.get("name").asText();
            String description = node.get("description").asText();
            String link = node.get("link").asText();

            id =  Long.parseLong(String.format("%s%d", name.hashCode(), description.length()));
            System.out.println(id);
            UsefulObject obj = new UsefulObject(id,name,description,link);
            datas.put(id,obj);
        }

        return datas;
    }
}