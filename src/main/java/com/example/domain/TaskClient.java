package com.example.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskClient
{
    private Long id;
    private TaskStatus status;
    private String taskName;
    private Date date;
    private String description;
    transient SimpleDateFormat formatter;

    public Long getId() {
        return id;
    }
    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskName() {
        return taskName;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
