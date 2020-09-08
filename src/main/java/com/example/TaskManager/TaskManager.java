package com.example.TaskManager;

import com.example.Task.Task;
import java.util.ArrayList;


public class TaskManager implements Managerable
{
    public ArrayList<Task> tasks;

    public TaskManager()
    {
        tasks = new ArrayList<Task>();
    }
    public void createTask(String taskName,String description)
    {
        tasks.add(new Task(taskName,description));
    }
    public ArrayList showAll()
    {
        return tasks;
    }
    public ArrayList showTask(int number)
    {
        if(number<0 || number >= tasks.size())
        {
            return null;
        }
        else
        {
            ArrayList<String> words = new ArrayList<String>();
            words.add(tasks.get(number).showName());
            words.add(tasks.get(number).showDescription());
            words.add(tasks.get(number).showStatus());
            words.add(tasks.get(number).showDate());

            return words;
        }
    }
    public void changeTask(int number,String taskName,String taskDescription,String taskStatus,String taskDate)
    {
        if(showTask(number) != null)
        {
            if(taskName != "")
            {
                tasks.get(number).changeName(taskName);
            }
            if(taskDescription != "")
            {
                tasks.get(number).changeDescription(taskDescription);
            }

            if(taskStatus.matches("NotReady")) tasks.get(number).changeStatusToNotReady();
            if(taskStatus.matches("Ready"))  tasks.get(number).changeStatusToReady();
            if(taskStatus.matches("NowInWork")) tasks.get(number).changeStatusToNowInWork();
            if(taskDate != "")
            {
                tasks.get(number).changeData(taskDate);
            }
        }
    }
    public void deleteTask(int number)
    {
        if(number<0 || number >= tasks.size())
        {
            return;
        }
        else
        {
            tasks.remove(number);
        }
    }

}
