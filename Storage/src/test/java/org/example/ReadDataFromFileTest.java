package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReadDataFromFileTest
{
    @Test
    public void unCorrectPathTest()
    {
        String path = "./src/test/resources";
        String fileName = "NoneFile.json";

        File file = new File(path, fileName);

        Assertions.assertFalse(file.exists());
    }

    @Test
    public void correctPathTest()
    {
        String path = "./src/test/resources";
        String fileName = "TestFile.json";

        File file = new File(path, fileName);

        Assertions.assertTrue(file.exists());
    }

    @Test
    public void NotEmptyDataFromFile() throws IOException
    {
        String path = "./src/test/resources";
        String fileName = "TestFile.json";

        File file = new File(path, fileName);

        Readable reader = new ReadDataFromFile();

        Map<Long, UsefulObject> datas = reader.readData(file.getPath());

        Assertions.assertFalse(datas.isEmpty());
    }

}
