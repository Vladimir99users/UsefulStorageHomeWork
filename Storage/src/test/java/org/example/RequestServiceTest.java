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

        assertEquals(objExpected, requestService.getUsefulObjectByID(objExpected.getId()));
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
        assertEquals(expectedID, newObj.getId());
    }

    @Test
    public void searchUsefulObjectToNameTest()
    {
        //Проверка на правильность поиска имени, суть в том, что мы должны находить массив объектов, с одинаковым именем, а это значит, что если первый элемент проходит, то и остальные тоже.
        String expectedName = "Fedor";

        UsefulObject usefulObjFirst = new UsefulObject(expectedName, "age 17", "https://Fedor.com");
        UsefulObject usefulObjSecond = new UsefulObject(expectedName, "age 182", "https://Fedor228.com");
        UsefulObject usefulObjectThird = new UsefulObject("Alina", "None", "https://Alina1997@gmail.com");

        RequestService requestService = new RequestService(new Requests());

        requestService.addUsefulObject(usefulObjFirst);
        requestService.addUsefulObject(usefulObjSecond);
        requestService.addUsefulObject(usefulObjectThird);

        List<UsefulObject> newObjs = requestService.getUsefulObjectsByName(expectedName);

        assertNotNull(newObjs);
        assertEquals(expectedName, newObjs.get(0).getName());
        assertEquals(expectedName, newObjs.get(1).getName());

    }

    private String getPath()
    {
        String resourceName = "TestFile.txt";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return file.getAbsolutePath();
    }
}
