package com.example.WorkingVsJson;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class WorkingVsJson implements ReadSaveable
{
    private FileReader fr;
    private FileWriter fw;
    private Scanner scan;
    private String path;
    public  WorkingVsJson(String _path) throws Exception
    {
        path = _path;
    }
    public void Save(String json) throws Exception
    {
        fw = new FileWriter(path);
        fw.write(json);
        fw.close();
    }
    public String Read() throws Exception
    {
        fr = new FileReader(path);
        scan = new Scanner(fr);
        String output = scan.nextLine();
        fr.close();
        return output;
    }

}