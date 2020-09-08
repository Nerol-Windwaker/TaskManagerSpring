package com.example.WorkingVsJson;

public interface ReadSaveable
{
    void Save(String json) throws Exception;
    String Read() throws Exception;
}
