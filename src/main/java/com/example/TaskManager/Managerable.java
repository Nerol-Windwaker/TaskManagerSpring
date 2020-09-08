package com.example.TaskManager;

import java.util.ArrayList;

public interface Managerable
{
    void createTask(String taskName,String description);
    ArrayList showAll();
    ArrayList showTask(int number);
    void changeTask(int number,String taskName,String taskDescription,String taskStatus,String taskDate);
    void deleteTask(int number);

}
