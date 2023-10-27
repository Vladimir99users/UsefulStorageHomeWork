package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RequestService
{
    private final Map<Long,UsefulObject> objectMap;
    private final Requestable requests;

    public RequestService(Requestable requests)
    {
        this.requests = requests;
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
        return requests.GetFindData(data, objectMap);
    }

    public List<UsefulObject> getUsefulObjectsByName(String data)
    {
        return requests.GetFindData(data, objectMap);
    }
}

