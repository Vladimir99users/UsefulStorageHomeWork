package org.example;

import org.junit.Test;


import java.io.File;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class RequestServiceTest
{
    @Test
    public  void addToStorageUsefulObjects()
    {
        //Тест на проверку добавления объекта.
        RequestService requestService = new RequestService(new Requests());

        UsefulObject objExpected = new UsefulObject("Drop down", "Отключиться", "https://yandex.ru");

        requestService.addUsefulObject(objExpected);

        assertEquals(objExpected, requestService.getUsefulObjectByID(objExpected.ID));
    }
    @Test
    public void correctIDTest()
    {
        // Тест на проверку ID - ID это хэш код имени
        String checkName = "Fedor";
        String desctiption = "age 17";
        long expectedID = Long.parseLong(String.format("%s%d", checkName.hashCode(), desctiption.length() ));

        RequestService requestService = new RequestService(new Requests());

        UsefulObject obj = new UsefulObject(checkName, desctiption, "https://Fedor.com");
        requestService.addUsefulObject(obj);

        UsefulObject newObj = requestService.getUsefulObjectByID(expectedID);

        assertNotNull(newObj);
        assertEquals(expectedID, newObj.ID);
    }

    @Test
    public void searchUsefulObjectToNameTest()
    {
        //Проверка на правильность поиска имени, суть в том, что мы должны находить массив объектов, с одинаковым именем, а это значит, что если первый элемент проходит, то и остальные тоже.
        String expectedName = "Fedor";

        UsefulObject usefulObj = new UsefulObject(expectedName, "age 17", "https://Fedor.com");
        RequestService requestService = new RequestService(new Requests());

        requestService.addUsefulObject(usefulObj);

        List<UsefulObject> newObjs = requestService.getUsefulObjectsByName(expectedName);

        assertNotNull(newObjs);
        assertEquals(expectedName, newObjs.get(0).Name);
    }

    private String getPath()
    {
        String resourceName = "TestFile.txt";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String absolutePath = file.getAbsolutePath();

        return absolutePath;
    }
}
