package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class RequestServiceTest
{

    private static RequestService requestService;

    @BeforeAll
    private static void setup()
    {
        Requestable requestable = new Requests();

        Readable readable = new ReadDataFromFile();
        Map<Long,UsefulObject> objectMap;

        try {
            objectMap = readable.readData(getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        requestService = new RequestService(requestable, objectMap);
    }
    @Test
    public void addToStorageUsefulObjects()
    {
        //Тест на проверку добавления объекта

        //Arrange
        UsefulObject objExpected = new UsefulObject();

        objExpected.setId(objExpected.hashCode());

        //act

        requestService.addUsefulObject(objExpected);

        UsefulObject objActual = requestService.getUsefulObjectByID(objExpected.getId());

        //Assert
        Assertions.assertNotNull(objActual);
        Assertions.assertEquals(objExpected, objActual );
    }
    @Test
    public void correctIDTest()
    {
        // Тест на проверку ID - ID это хэш код имени

        UsefulObject objExpected = new UsefulObject();

        long expectedID = objExpected.getId();

        requestService.addUsefulObject(objExpected);

        UsefulObject newObj = requestService.getUsefulObjectByID(expectedID);

        Assertions.assertNotNull(newObj);
        Assertions.assertEquals(expectedID, newObj.getId());
    }

    @Test
    public void searchUsefulObjectToNameTest()
    {
       //Проверка на правильность поиска имени, суть в том, что мы должны находить массив объектов, с одинаковым именем, а это значит, что если первый элемент проходит, то и остальные тоже.
        String expectedName = "Test";

        List<UsefulObject> newObjs = requestService.getUsefulObjectsByName(expectedName);

        Assertions.assertNotNull(newObjs);

        for (UsefulObject tmpObj : newObjs)
        {
            Assertions.assertEquals(expectedName, tmpObj.getName());
        }

    }

    private static String getPath() throws IOException
    {
        String resourceName = "TestFile.json";

        ClassLoader classLoader = RequestServiceTest.class.getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return file.getAbsolutePath();
    }
}
