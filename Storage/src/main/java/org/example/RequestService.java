package org.example;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestService
{

    private Map<Long,UsefulObject> objectMap;
    private final Requestable requests;

    public RequestService(@NonNull Requestable requests) {
        this.requests = requests;
        objectMap = new HashMap<Long,UsefulObject>();
    }

    public void readJsonFile(String path) throws IOException {
        Readable readData = new ReadDataFromFile();
        objectMap = readData.readData(path);
    }

    public void addUsefulObject(@NonNull UsefulObject obj)
    {


        if(objectMap.containsKey(obj.getId()))
        {
            System.out.println("The object has already been added to the collection");
            return;
        }

        objectMap.put(obj.getId(), obj);
    }
    public UsefulObject getUsefulObjectByID(Long data)
    {
        return requests.getFindData(data, objectMap);
    }

    public List<UsefulObject> getUsefulObjectsByName(String data)
    {
        return requests.getFindData(data, objectMap);
    }
}

