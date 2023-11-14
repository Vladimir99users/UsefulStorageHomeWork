package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadDataFromFileTest
{
    private static Readable reader;

    @BeforeAll
    private static void setup()
    {
        reader = new ReadDataFromFile();
    }

    @Test
    public void readEmptyJsonFileTest() throws IOException
    {
        //Тест проверяет, что будет если считать пустой jsonFile

        //Arrange
        String emptyPath = "./src/test/resources/NoneFile.json";

        File file = new File(emptyPath);

        //Act

        Map<Long, UsefulObject> datas = reader.readData(emptyPath);

        //Assert
        Assertions.assertTrue(file.exists());
        Assertions.assertTrue(datas.isEmpty());
    }


    @Test
    public void readFullJsonFileTest() throws IOException
    {
        //В данном тесте проверяем, на то, что данные считались и то, что объект существует в списке.
        //Arrange
        String fullPath = "./src/test/resources/TestFile.json";
        File file = new File(fullPath);

        //act

        Map<Long, UsefulObject> datas = reader.readData(file.getPath());
        List<UsefulObject> objectList = reader.getListValue(file.getPath());

        UsefulObject obj = objectList.get(0);
        obj.setId(obj.hashCode());

        //Assert

        Assertions.assertTrue(file.exists());
        Assertions.assertFalse(datas.isEmpty());
        Assertions.assertFalse(objectList.isEmpty());
        Assertions.assertTrue(datas.containsKey(obj.getId()));
    }

}
