package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindByName implements findableString
{
    private final List<UsefulObject> usefulObjects = new ArrayList<UsefulObject>();
    public List<UsefulObject> GetFindData(String name, Map<Long,UsefulObject> objectMap)
    {
        usefulObjects.clear();

        for (UsefulObject obj : objectMap.values())
        {
            if (name.equalsIgnoreCase(obj.Name))
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
