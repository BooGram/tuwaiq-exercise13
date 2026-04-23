package com.example.tuwaiqtodo.Controller;

import com.example.tuwaiqtodo.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    ArrayList<Task> tasks = new ArrayList<>();


    @PostMapping("/create")
    public String addTask(@RequestBody Task taskTracker) {
        tasks.add(taskTracker);
        return ("Task added successfully");
    }

    @GetMapping("/display")

    public ArrayList<Task> displayTasks() {

        return tasks;

    }

    @PutMapping("/update/{ID}")
    public String updateTask(@PathVariable int ID, @RequestBody Task trackerSystem) {
        tasks.set(ID, trackerSystem);
        return "Task updated successfully";
    }

    @DeleteMapping("/delete/{ID}")
    public String deleteTask(@PathVariable int ID) {
        tasks.remove(ID);
        return ("Task deleted successfully");
    }

    @PutMapping("/update-status/{ID}/{status}")
    public String updateStatus(@PathVariable int ID, @PathVariable boolean status) {
        tasks.get(ID).setStatus(status);

        return ("Task status updated successfully");
    }

    @GetMapping("/search/{title}")
    public Task searchByTitle(@PathVariable String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return task;
            }
        }
        return null;
    }

}
