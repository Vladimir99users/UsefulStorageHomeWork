package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RequestService
{
    private Map<Integer,UsefulObject> _dictionary;

    public RequestService(Map<Integer,UsefulObject> dictionary)
    {
        _dictionary = dictionary;
    }
    public  void RunService()
    {


        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        System.out.println("Здравствуйте, что бы вы хотели сделать?");
        System.out.println("1 - Display a record");
        System.out.println("2 - Search records by name");
        System.out.println("3 - Exit");

        while (!isExit)
        {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 :
                    System.out.print("Enter ID: ");
                    int indexFind = scanner.nextInt();
                    displayRecordById(indexFind);
                    break;
                case 2 :
                    System.out.print("Enter string: ");
                    String nameSearch = scanner.nextLine();
                    displayRecordsByName(nameSearch);
                    break;
                case 3 :
                    isExit = true;
                    break;
                default :
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public UsefulObject displayRecordById(int choice)
    {
        Scanner scanner = new Scanner(System.in);


        UsefulObject obj = _dictionary.get(choice);

        if(obj == null)
        {
            System.out.println("ID is not founded");
        }

        System.out.println(obj.GetStringData());
        return  obj;
    }

    public UsefulObject  displayRecordsByName(String name)
    {
        UsefulObject useful = new UsefulObject();
        int stack = 0;

        for (UsefulObject obj : _dictionary.values() )
        {
            if(name.equalsIgnoreCase(obj.Name))
            {
                stack++;
                System.out.println(obj.GetStringData());
                useful = obj;
            }
        }

        if(stack == 0)
        {
            System.out.println("Запись не найдена.\n");
        }

        return  useful;
    }

}
