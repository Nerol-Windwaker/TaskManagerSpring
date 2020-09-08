package com.example.Task;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import com.example.enums.*;

public class Task implements Taskable
{
    public  int id;
    public TaskStatus status;
    public String taskName;
    public Date date;
    public String description;
    transient SimpleDateFormat formatter;

    public Task()
    {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    public Task(String _taskName,String _description)
    {
        status = TaskStatus.NOTREADY;
        date = new Date();
        taskName = _taskName;
        description = _description;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    public String showStatus()
    {
        return  status.toString();
    }
    public String showName()
    {
        return taskName;
    }
    public String showDescription()
    {
        return description;
    }
    public String showDate()
    {
        return formatter.format(date);
    }

    public void changeStatusToReady()
    {
        status = TaskStatus.READY;
    }
    public void changeStatusToNowInWork()
    {
        status = TaskStatus.NOWINWORK;
    }
    public void changeStatusToNotReady()
    {
        status = TaskStatus.NOTREADY;
    }
    public void changeName(String newName)
    {
        taskName = newName;
    }
    public boolean changeData(String newDate)
    {
        try {
            date = formatter.parse(newDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public void changeDescription(String newDescription)
    {
        description = newDescription;
    }
}
