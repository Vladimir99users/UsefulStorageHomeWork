package org.example;

public class UsefulObject
{
    public int ID;
    public  String Name;
    public  String Description;
    public  String URL;

    public  UsefulObject()
    {
        this(-1, "default name", "default Description", "default url"  );
    }
    public UsefulObject(int id, String name, String description, String uRL )
    {
        ID = id;
        Name = name;
        Description = description;
        URL = uRL;
    }
    
    public String GetStringData()
    {
       return String.format("ID %d , Name %s , Description %s, URL %s ", ID, Name, Description, URL);
    }
}
