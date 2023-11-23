package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RequestServiceTest
{

    private static RequestService requestService;

    private static UsefulObject firstObject = new UsefulObject();
    private static UsefulObject secondObject = new UsefulObject();

    @BeforeAll
    private static void setup()
    {
        Requestable requestable = new Requests();

        // Добавил в ручную 1-2 объекта, без считывания

        firstObject.setId(firstObject.hashCode());
        firstObject.setName("Oleg");

        secondObject.setId(firstObject.hashCode());
        secondObject.setName("Kolya");

        // заполнил тут мапу
        Map<Long,UsefulObject> objectMap = new HashMap<>();

        objectMap.put(firstObject.getId(),firstObject);
        objectMap.put(secondObject.getId(), secondObject);

        // создал и вставил всё объекты в сервис
        requestService = new RequestService(requestable);
        requestService.addAllUsefulObject(objectMap);
    }
    @Test
    public void addToStorageUsefulObjects()
    {
        //Тест на проверку добавления объекта
        // я уже добавил колю, а значит проверяю, что первый найденный элемент это он
        // так как добавил в ручную только одного колю

        //Arrange
        String expectedName = firstObject.getName();

        //act
        UsefulObject objActual = requestService.getUsefulObjectsByName(expectedName).get(0);

        //Assert
        Assertions.assertNotNull(objActual);
        Assertions.assertEquals(expectedName, objActual.getName() );
    }
    @Test
    public void correctIDTest()
    {
        // Тест на проверку ID - ID это хэш код имени
        //Arrange
        long expectedID = secondObject.getId();

        //act
        UsefulObject newObj = requestService.getUsefulObjectByID(expectedID);

        //Assert
        Assertions.assertNotNull(newObj);
        Assertions.assertEquals(expectedID, newObj.getId());
    }

    @Test
    public void searchUsefulObjectToNameTest()
    {
        //Проверка на правильность поиска имени, суть в том, что мы должны находить массив объектов, с одинаковым именем, а это значит, что если первый элемент проходит, то и остальные тоже.
        //Arrange
        String expectedName = secondObject.getName();

        //act
        List<UsefulObject> newObjs = requestService.getUsefulObjectsByName(expectedName);

        //Assert
        Assertions.assertNotNull(newObjs);

        for (UsefulObject tmpObj : newObjs)
        {
            Assertions.assertEquals(expectedName, tmpObj.getName());
        }

    }

    private static String getPath()
    {
        String resourceName = "TestFile.json";

        ClassLoader classLoader = RequestServiceTest.class.getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return file.getAbsolutePath();
    }
}
