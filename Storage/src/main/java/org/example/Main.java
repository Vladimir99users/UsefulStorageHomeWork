package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws Exception {
        if(args.length > 1)
        {
            throw new Exception("Many arguments!");
        }

        String path = args[0];
        Readable readFile = new ReadDataFromFile();
        String finallyStr =  readFile.readData(path);

        List<UsefulObject> usefulObjects = GetDataFromString(finallyStr);

        RequestService requestService = new RequestService();


        SetDataFromRequestService(requestService, usefulObjects);


        DisplayService displayService = new DisplayService(requestService);

        displayService.RunService();
    }

    private  static  List<UsefulObject> GetDataFromString(String finallyStr)
    {
        List<UsefulObject> usefulObjects = new ArrayList<UsefulObject>();

        String[] str = finallyStr.split("\n");
        for (String s : str) {
            String[] value = s.split(",");

            UsefulObject newObj = new UsefulObject
                    (
                            value[0],
                            value[1],
                            value[2]
                    );

            usefulObjects.add(newObj);
        }
        return usefulObjects;
    }

    private  static void SetDataFromRequestService(RequestService requestService, List<UsefulObject> datas)
    {
        for (UsefulObject data : datas)
        {
            requestService.AddUsefulObject(data);
        }
    }
}