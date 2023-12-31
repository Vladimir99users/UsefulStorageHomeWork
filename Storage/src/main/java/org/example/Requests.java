package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class Requests implements Requestable
{
    public UsefulObject getFindData(Long choice, Map<Long,UsefulObject> objectMap)
    {
        UsefulObject obj = objectMap.get(choice);

        if (Objects.isNull(obj))
        {
            System.out.println("ID is not founded");
        }

        return obj;
    }


    public List<UsefulObject> getFindData(String name, Map<Long,UsefulObject> objectMap)
    {
        List<UsefulObject> usefulObjects = new ArrayList<UsefulObject>();

        for (UsefulObject obj : objectMap.values())
        {
            if (name.equalsIgnoreCase(obj.getName()))
            {
                usefulObjects.add(obj);
            }
        }

        if (usefulObjects.isEmpty())
        {
            System.out.println("Запись не найдена.\n");
        }

        return usefulObjects;
    }
}
