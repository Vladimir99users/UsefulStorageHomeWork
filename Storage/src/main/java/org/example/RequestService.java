package org.example;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestService
{
    private final Requestable request;
    private final Map<Long,UsefulObject> objectMap;

    public RequestService(Requestable request)
    {
        this.request = request;
        this.objectMap = new HashMap<>();
    }

    public void addAllUsefulObject(Map<Long,UsefulObject> objectMap)
    {
        this.objectMap.putAll(objectMap);
    }
    public boolean addUsefulObject(@NonNull UsefulObject obj)
    {
        if(objectMap.containsKey(obj.getId()))
        {
            System.out.println("The object has already been added to the collection");
            return false;
        }

        objectMap.put(obj.getId(), obj);
        return true;
    }

    public boolean deletedUsefulObject(@NonNull UsefulObject obj)
    {
        if(objectMap.containsKey(obj.getId()))
        {
            System.out.println("The object has already been added to the collection");
            return false;
        }

        objectMap.remove(obj.getId());
        return true;
    }
    public UsefulObject getUsefulObjectByID(Long data)
    {
        return request.getFindData(data, objectMap);
    }

    public List<UsefulObject> getUsefulObjectsByName(String data)
    {
        return request.getFindData(data, objectMap);
    }
}

