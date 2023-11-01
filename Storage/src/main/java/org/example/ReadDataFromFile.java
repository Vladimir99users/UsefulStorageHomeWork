package org.example;

import java.io.*;

public  class  ReadDataFromFile implements Readable
{

    @Override
    public String readData(String path)
    {
        File file = new File(path);
        String str = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                str += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }
}