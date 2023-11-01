package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;

public class DisplayService
{
    private RequestService service;
    public DisplayService(RequestService service)
    {
        this.service = service;
    }
    public void runService()
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
                    long indexFind = scanner.nextInt();
                    displayObjectToID(indexFind);
                    break;
                case 2 :
                    System.out.print("Enter string: ");
                    String nameSearch = scanner.nextLine();
                    displayObjectToName( nameSearch);
                    break;
                case 3 :
                    isExit = true;
                    break;
                default :
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private void displayObjectToName(String id)
    {
        List<UsefulObject> objs = new ArrayList<>();

        objs = service.getUsefulObjectsByName(id);

        for (UsefulObject obj : objs)
        {
            displayData(obj);
        }
    }

    private void displayObjectToID(Long id)
    {
        UsefulObject obj = service.getUsefulObjectByID(id);

        displayData(obj);
    }

    private void displayData(UsefulObject obj)
    {
        if(Objects.isNull(obj))
            return;


        System.out.println(String.format("Object is %s", obj.getStringData()));
    }
}
