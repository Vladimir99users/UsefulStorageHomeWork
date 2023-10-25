package org.example;

public class UsefulObject
{
    public long ID;
    public  String Name;
    public  String Description;
    public  String URL;

    public UsefulObject(String name, String description, String uRL )
    {
        ID =  Long.parseLong(String.format("%s%d", name.hashCode(), description.length()));
        Name = name;
        Description = description;
        URL = uRL;
    }
    
    public String GetStringData()
    {
       return String.format("ID %d , Name %s , Description %s, URL %s ", ID, Name, Description, URL);
    }
}
