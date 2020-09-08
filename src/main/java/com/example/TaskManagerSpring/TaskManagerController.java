package com.example.TaskManagerSpring;
import com.example.TaskManager.TaskManager;
import com.google.gson.*;
import com.example.TaskManager.TaskManager;
import com.example.WorkingVsJson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class TaskManagerController
{
    public TaskManagerController() throws Exception
    {
    }
    Gson gson = new Gson();
    TaskManager taskManager = new TaskManager();
    WorkingVsJson writer = new WorkingVsJson("tasks.json");

    @PostConstruct
    private void start() throws Exception {
        try
        {
            taskManager = gson.fromJson(writer.Read(), TaskManager.class);
        }
        catch (Exception e)
        {
            taskManager = new TaskManager();
            writer.Save(gson.toJson(taskManager));
        }
    }

    @GetMapping
    public String main(Map<String, Object> model)
    {
        return "main";
    }
    @PostMapping("/tasks/task")
    public String changeTask
    (
                    @RequestParam(name="id", required=true, defaultValue="0") int  id,
                    @RequestParam String newTaskName,
                    @RequestParam String newTaskDescription,
                    @RequestParam String newTaskStatus,
                    @RequestParam String newTaskDate,
                    Map<String,Object> model
    )
    {
        taskManager.changeTask(id,newTaskName,newTaskDescription,newTaskStatus,newTaskDate);
        try {
            writer.Save(gson.toJson(taskManager));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.put("taskName", taskManager.showTask(id).get(0));//Show task returned ArrayList( temporarily)
        model.put("taskDescription", taskManager.showTask(id).get(1));
        model.put("taskStatus",taskManager.showTask(id).get(2));
        model.put("taskDate", taskManager.showTask(id).get(3));


        return "task";
    }
    @PostMapping("deleteTask")
    public String deleteTask
    (
                    @RequestParam(name="deleteNumber", required=true, defaultValue="-1") int  id,
                    Map<String,Object> model
    )
    {
        if(id!= -1) taskManager.deleteTask(id);
        for(int i =0; i<taskManager.showAll().size();i++ ) //temporarily
        {
            taskManager.tasks.get(i).id = i;
        }
        try {
            writer.Save(gson.toJson(taskManager));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.put("tasks",taskManager.showAll());
        return "tasks";
    }
    @PostMapping("newTask")
    public String newTask
    (
                    @RequestParam(name="taskName", required=true, defaultValue="None") String taskName,
                    @RequestParam(name="taskDescription", required=true, defaultValue="None") String taskDescription,
                    Map<String,Object> model
    )
    {
        taskManager.createTask(taskName,taskDescription);
        for(int i =0; i<taskManager.showAll().size();i++ ) //temporarily
        {
            taskManager.tasks.get(i).id = i;
        }
        try {
            writer.Save(gson.toJson(taskManager));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.put("tasks",taskManager.showAll());
        return "tasks";
    }
    @GetMapping("/tasks")
    public String tasks(Map<String, Object> model)
    {
        for(int i =0; i<taskManager.showAll().size();i++ )//temporarily
        {
            taskManager.tasks.get(i).id = i;
        }
        model.put("tasks",taskManager.showAll());
        return "tasks";
    }
    @GetMapping("/tasks/task")
    public String task
    (
                @RequestParam(name="id", required=true, defaultValue="0") int  id,
                @RequestParam(name="taskName", required=false) String taskName,
                @RequestParam(name="taskDescription", required=false) String taskDescription,
                @RequestParam(name="taskStatus", required=false) String taskStatus,
                @RequestParam(name="taskDate", required=false) String taskDate,
                Map<String, Object> model
    )
    {
        for(int i =0; i<taskManager.showAll().size();i++ )
        {
            taskManager.tasks.get(i).id = i;
        }
        model.put("taskName", taskManager.tasks.get(id).showName());
        model.put("taskDescription", taskManager.tasks.get(id).showDescription());
        model.put("taskStatus", taskManager.tasks.get(id).showStatus());
        model.put("taskDate", taskManager.tasks.get(id).showDate());
        return "task";
    }
}