package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Readable
{
    public Map<Long, UsefulObject> readData(String path);

}
