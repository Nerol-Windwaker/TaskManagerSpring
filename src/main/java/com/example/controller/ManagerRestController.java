package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.domain.Task;
import com.example.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerRestController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/{id}")
    Optional<Task> oneTask(@PathVariable Long id)
    {
        return taskRepository.findById(id);
    }
    @GetMapping("/tasks")
    List<Task> all()
    {
       return (List<Task>) taskRepository.findAll();
    }

    @PostMapping("/tasks")
    Task newTask(@RequestBody Task newTask)
    {
        newTask.setStatusNotReady();
        newTask.RetryDate();
        return  taskRepository.save(newTask);
    }

    @PutMapping("/tasks/{id}")
    Task editTask(@RequestBody Task newTask, @PathVariable Long id)
    {
        Task task = taskRepository.findById(id).get();
        if(newTask.getTaskName() != null) task.setTaskName(newTask.getTaskName());
        if(newTask.getDescription() != null) task.setDescription(newTask.getDescription());
        if(newTask.getDate() != null) task.setDate(newTask.getDate());
        if(newTask.getStatus() != null) {
            if (newTask.getStatus().toString().toUpperCase().matches("NotReady".toUpperCase())) task.setStatusNotReady();
            else if (newTask.getStatus().toString().toUpperCase().matches("Ready".toUpperCase())) task.setStatusReady();
            else if (newTask.getStatus().toString().toUpperCase().matches("NowInWork".toUpperCase())) task.setStatusNowInWork();
        }
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable Long id)
    {
        taskRepository.deleteById(id);
    }

}
