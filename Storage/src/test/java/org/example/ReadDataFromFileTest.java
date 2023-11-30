package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ReadDataFromFileTest
{
    private final Readable reader = new ReadDataFromFile();


    @Test
    @DisplayName("Read Empty Json File")
    public void readEmptyJsonFileTest() throws IOException
    {
        //Тест проверяет, что будет если считать пустой jsonFile

        //Arrange
        String emptyPath = "./src/test/resources/Empty.json";

        File file = new File(emptyPath);

        //Act

        Map<Long, UsefulObject> datas = reader.readData(emptyPath);

        //Assert
        Assertions.assertTrue(file.exists());
        Assertions.assertTrue(datas.isEmpty());
    }

    @Test
    public void readNoNExistingFileTest()
    {
        // проверка на не существующий файл

        //Arrange
        String NoNExisting = ".\\src\\test\\resources\\NonFile.json";
        String expectedExpeption = "An error occurred while reading file: java.io.FileNotFoundException: " + NoNExisting + " (Не удается найти указанный файл)";

        //Act
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            reader.readData(NoNExisting);
        });

        //Assert
        Assertions.assertEquals(expectedExpeption, exception.getMessage());
    }

    @Test
    @DisplayName("ReadingFileWithMissingData")
    public void readMissingDataFileTest()
    {
        //Arrange
        String missingDataFile = "./src/test/resources/BadFile.json";

        String expectedExpeption = "@NotNull method org/example/UsefulObject.getName must not return null";

        //Act
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            reader.readData(missingDataFile);
        });


        //Assert
        Assertions.assertEquals(expectedExpeption, exception.getMessage());
    }

    @Test
    @DisplayName("Read Normal Json File")
    public void readFullJsonFileTest() throws IOException
    {
        //В данном тесте проверяем, на то, что данные считались и то, что объект существует в списке.
        //Arrange
        String fullPath = "./src/test/resources/TestFile.json";
        File file = new File(fullPath);

        //act

        Map<Long, UsefulObject> datas = reader.readData(file.getPath());
        List<UsefulObject> objectList = new ArrayList<>(reader.readData(file.getPath()).values());

        UsefulObject obj = objectList.get(0);

        //Assert

        Assertions.assertTrue(file.exists());
        Assertions.assertFalse(datas.isEmpty());
        Assertions.assertFalse(objectList.isEmpty());
        Assertions.assertTrue(datas.containsKey(obj.getId()));
    }

}
