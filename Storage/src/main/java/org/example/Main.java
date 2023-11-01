package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws Exception {
        if(args.length > 1)
        {
            throw new Exception("Many arguments!");
        }

        String path = args[0];
        String finallyStr = "";

        try
        {
            Readable readFile = new ReadDataFromFile();

            finallyStr =  readFile.readData(path);

        } catch (Exception e)
        {
            throw new Exception(e);
        }


        if(finallyStr.isEmpty())
        {
            System.out.println("Финальная строка оказалась пустой!");
            return;
        }

        List<UsefulObject> usefulObjects = getDataFromString(finallyStr);

        RequestService requestService = new RequestService(new Requests());


        setDataFromRequestService(requestService, usefulObjects);


        DisplayService displayService = new DisplayService(requestService);

        displayService.runService();
    }

    private  static  List<UsefulObject> getDataFromString(String finallyStr)
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

    private  static void setDataFromRequestService(RequestService requestService, List<UsefulObject> datas)
    {
        for (UsefulObject data : datas)
        {
            requestService.addUsefulObject(data);
        }
    }
}