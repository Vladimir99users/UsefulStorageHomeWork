package org.example;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class FindByID implements findableID
{
    public UsefulObject GetFindData(Long choice, Map<Long,UsefulObject> objectMap)
    {
        UsefulObject obj = objectMap.get(choice);

        if (Objects.isNull(obj))
        {
            System.out.println("ID is not founded");
        }

        return obj;
    }
}
