package com.example.controller;

import com.example.domain.TaskClient;
import com.example.repos.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/task-manager")
public class ManagerClientController
{
    HttpHeaders headers;
    String restUrl = "http://localhost:8080";
    RestTemplate restTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @PostConstruct
    private void start()
    {
        restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }
    @GetMapping
    public String main(Map<String, Object> model)
    {
        return "main";
    }

    @GetMapping("/tasks")
    public String tasks(Map<String, Object> model)
    {
        ResponseEntity<TaskClient[]> response = restTemplate.getForEntity(restUrl+"/tasks", TaskClient[].class);
        TaskClient[] tasks = response.getBody();
        model.put("tasks",tasks);
        return "tasks";
    }
    @GetMapping("tasks/{id}")
    public String task
            (
                    @PathVariable Long id, Map<String, Object> model
            )
    {
        ResponseEntity<TaskClient> response = restTemplate.getForEntity(restUrl+"/tasks/"+id.toString(), TaskClient.class);
        TaskClient task = response.getBody();
        model.put("taskName", task.getTaskName());
        model.put("taskDescription", task.getDescription());
        model.put("taskStatus", task.getStatus());
        model.put("taskDate", task.getDate());
        return "task";
    }


    @PostMapping("add")
    public String newTask
            (
                    @RequestParam(name="taskName", required=true, defaultValue="None") String taskName,
                    @RequestParam(name="taskDescription", required=true, defaultValue="None") String taskDescription,
                    Map<String,Object> model
            )
    {

        Map<String , String> map = new HashMap<>();
        map.put("taskName", taskName);
        map.put("description", taskDescription);
        HttpEntity<Map<String, String>> entity  = new HttpEntity<>(map, headers);

        restTemplate.postForEntity(restUrl+"/tasks", entity, String.class);

        ResponseEntity<TaskClient[]> response = restTemplate.getForEntity(restUrl+"/tasks", TaskClient[].class);
        TaskClient[] tasks = response.getBody();
        model.put("tasks",tasks);
        return "tasks";
    }
    @PostMapping("/tasks/{id}")
    public String changeTask
    (
                    @PathVariable Long id,
                    @RequestParam String newTaskName,
                    @RequestParam String newTaskDescription,
                    @RequestParam String newTaskStatus,
                    @RequestParam String newTaskDate,
                    Map<String,Object> model
    )
    {
        Map<String , String> map = new HashMap<>();

        if(newTaskName != "") map.put("taskName", newTaskName);

        if(newTaskDescription != "") map.put("description", newTaskDescription);

        if(!newTaskStatus.matches("None")) map.put("status", newTaskStatus.toUpperCase());

        if(newTaskDate != "")
        {
            map.put("date",newTaskDate);
        }
        restTemplate.put(restUrl+"/tasks/"+id.toString(),map);

        ResponseEntity<TaskClient> response = restTemplate.getForEntity(restUrl+"/tasks/"+id.toString(), TaskClient.class);
        TaskClient task = response.getBody();

        model.put("taskName", task.getTaskName());
        model.put("taskDescription", task.getDescription());
        model.put("taskStatus", task.getStatus());
        model.put("taskDate", task.getDate());
        return "task";
    }
    @PostMapping("delete")
    public String deleteTask
    (
                    @RequestParam(name="deleteNumber", required=true, defaultValue="-1") Long  id,
                    Map<String,Object> model
    )
    {
        restTemplate.delete(restUrl+"/tasks/"+id.toString());

        ResponseEntity<TaskClient[]> response = restTemplate.getForEntity(restUrl+"/tasks", TaskClient[].class);
        TaskClient[] tasks = response.getBody();
        model.put("tasks",tasks);
        return "tasks";
    }



}