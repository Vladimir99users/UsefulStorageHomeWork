package org.example;

import java.util.List;
import java.util.Map;

public interface Requestable
{
    public UsefulObject GetFindData(Long data, Map<Long,UsefulObject> objectMap);
    public List<UsefulObject> GetFindData(String data, Map<Long,UsefulObject> objectMap);
}