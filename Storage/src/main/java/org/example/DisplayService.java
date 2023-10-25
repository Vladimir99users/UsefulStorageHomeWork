package org.example;

import java.util.Scanner;
import java.util.List;

public class DisplayService
{
    private RequestService service;
    public DisplayService(RequestService service)
    {
        this.service = service;
    }
    public void RunService()
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
                    findDataByID(indexFind);
                    break;
                case 2 :
                    System.out.print("Enter string: ");
                    String nameSearch = scanner.nextLine();
                    findDataByName( nameSearch);
                    break;
                case 3 :
                    isExit = true;
                    break;
                default :
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private void findDataByName(String data)
    {
        List<UsefulObject> objs = service.getUsefulObjectsByName(data);

        for (UsefulObject obj : objs)
        {
            DisplayData(obj);
        }
    }

    private void findDataByID(Integer data)
    {
        UsefulObject obj = service.getUsefulObjectByID(data);

        DisplayData(obj);
    }

    private void DisplayData(UsefulObject obj)
    {
        System.out.println
        (
               String.format("Object is %s", obj.GetStringData())
        );
    }
}
