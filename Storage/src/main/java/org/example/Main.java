package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length > 1)
        {
            throw new Exception("Many arguments!");
        }

        String path = args[0];
        IReadable readFile = new ReadDataFromFile();
        String finallyStr =  readFile.Read(path);

        List<UsefulObject> usefulObjects = GetDataFromString(finallyStr);

        Map<Integer,UsefulObject> dictionary = SetDataFromDictionary(usefulObjects);

        RequestService service = new RequestService(dictionary);

        service.RunService();
    }

    private  static  List<UsefulObject> GetDataFromString(String finallyStr)
    {
        List<UsefulObject> usefulObjects = new ArrayList<UsefulObject>();

        String[] str = finallyStr.split("\n");
        for (String s : str) {
            String[] value = s.split(",");

            UsefulObject newObj = new UsefulObject
                    (
                            Integer.parseInt(value[0]),
                            value[1],
                            value[2],
                            value[3]
                    );

            usefulObjects.add(newObj);
        }
        return usefulObjects;
    }

    private  static Map<Integer,UsefulObject> SetDataFromDictionary(List<UsefulObject> datas)
    {
        Map<Integer,UsefulObject> dictionary = new HashMap<Integer,UsefulObject>();

        for (UsefulObject data : datas)
        {
            dictionary.put(data.ID, data);
        }

        return  dictionary;
    }
}