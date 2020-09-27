package com.example.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Entity
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Task()
    {
        this.formatter = new SimpleDateFormat("yyyy-MM-dd");
    }
    public Task(String taskName,String description)
    {
        this.status = TaskStatus.NOTREADY;
        this.date = new Date();
        this.taskName = taskName;
        this.description = description;
        this.formatter = new SimpleDateFormat("yyyy-MM-dd");
    }
    public void RetryDate()
    {
        this.date = new Date();
    }
    public Long getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getTaskName() {
        return taskName;
    }

    public Date getDate()
    {
        return this.date;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setStatusReady()
    {
        status = TaskStatus.READY;
    }
    public void setStatusNowInWork()
    {
        status = TaskStatus.NOWINWORK;
    }
    public void setStatusNotReady()
    {
        status = TaskStatus.NOTREADY;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDate(String date)
    {
        try
        {
            this.date = formatter.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public void setDate(Date date) {this.date = date;}

    public void setDescription(String description) {
        this.description = description;
    }

    private TaskStatus status;
    private String taskName;
    private Date date;
    private String description;
    transient SimpleDateFormat formatter;

}
