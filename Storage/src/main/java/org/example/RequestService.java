package org.example;

import org.w3c.dom.events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RequestService
{
    private final Map<Long,UsefulObject> objectMap;
    // можно потом интерфейсы для поиска, заменить на Generic если возможно, а также передавать в конструкторе.
    private final findableID findableID = new FindByID();
    private final findableString findableString = new FindByName();

    public RequestService()
    {
        objectMap = new HashMap<Long,UsefulObject>();
    }

    public void AddUsefulObject(UsefulObject obj)
    {
        Objects.requireNonNull(obj, "Передаётся нулевой объект");

        if(objectMap.containsValue(obj))
        {
            System.out.println("The object has already been added to the collection");
            return;
        }

        objectMap.put(obj.ID, obj);
    }
    public UsefulObject getUsefulObjectByID(Long data)
    {
        return findableID.GetFindData(data, objectMap);
    }

    public List<UsefulObject> getUsefulObjectsByName(String data)
    {
        return findableString.GetFindData(data, objectMap);
    }
}

