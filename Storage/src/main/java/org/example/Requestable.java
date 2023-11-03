package org.example;

import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;


public interface Requestable
{

    public UsefulObject getFindData(Long data, Map<Long,UsefulObject> objectMap);
    public List<UsefulObject> getFindData(String data, Map<Long,UsefulObject> objectMap);
}