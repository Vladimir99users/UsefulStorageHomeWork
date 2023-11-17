package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public  class  ReadDataFromFile implements Readable
{

    public Map<Long, UsefulObject> readData(String path)
    {
        //Строим HashMap по данным с файла.
        Map<Long, UsefulObject> datas = new HashMap<>();

        List<UsefulObject> usefulObjects = getListValue(path);

        for (UsefulObject usefulObject : usefulObjects)
        {
            long id =  usefulObject.hashCode();

            usefulObject.setId(id);

            datas.put(id, usefulObject);
        }

        return datas;
    }
    public List<UsefulObject> getListValue(String path)
    {
        //Получаем данные через мапер и файл
        File file = new File(path);
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