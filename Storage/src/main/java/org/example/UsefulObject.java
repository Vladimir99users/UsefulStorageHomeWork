package org.example;


public class UsefulObject
{
    private long id;
    private  String name;
    private  String description;
    private  String url;

    public UsefulObject(String name, String description, String uRL )
    {
        id =  Long.parseLong(String.format("%s%d", name.hashCode(), description.length()));
        this.name = name;
        this.description = description;
        url = uRL;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getURL()
    {
        return url;
    }
    
    public String getStringData()
    {
       return String.format("ID %d , Name %s , Description %s, URL %s ", id, name, description, url);
    }
}
