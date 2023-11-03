package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.io.File;
import java.io.IOException;
import java.util.List;


public class RequestServiceTest
{

    private  RequestService requestService;

    @BeforeEach
    public  void setup()
    {
        Requestable request = new Requests();
        requestService = new RequestService(request);

        try {
            requestService.readJsonFile(getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public  void addToStorageUsefulObjects()
    {
        //Тест на проверку добавления объекта

        //Arrange
        String nameTest = "kkk";
        String descriptions = "Enable";
        String link = "https://yandex.ru";
        long idTest = Long.parseLong(String.format("%s%d", nameTest.hashCode(), descriptions.length()));

        //act
        UsefulObject objExpected = new UsefulObject(idTest, nameTest, descriptions, link);

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
        String checkName = "Test";
        String desctiption = "Enable";
        long expectedID = Long.parseLong(String.format("%s%d", checkName.hashCode(), desctiption.length() ));

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

    private String getPath() throws IOException
    {
        String resourceName = "TestFile.json";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return file.getAbsolutePath();
    }
}
