package com.example.tuwaiqtodo.Controller;

import com.example.tuwaiqtodo.ApiResponse.ApiResponse;
import com.example.tuwaiqtodo.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    ArrayList<Task> tasks = new ArrayList<>();


    @PostMapping("/create")
    public ApiResponse addTask(@RequestBody Task taskTracker) {
        tasks.add(taskTracker);
        return new ApiResponse("Task added successfully");
    }

    @GetMapping("/display")

    public ArrayList<Task> displayTasks() {

        return tasks;

    }

    @PutMapping("/update/{ID}")
    public ApiResponse updateTask(@PathVariable int ID, @RequestBody Task trackerSystem) {
        tasks.set(ID, trackerSystem);
        return new ApiResponse("Task updated successfully");
    }

    @DeleteMapping("/delete/{ID}")
    public ApiResponse deleteTask(@PathVariable int ID) {
        tasks.remove(ID);
        return new ApiResponse("Task deleted successfully");
    }

    @PutMapping("/update-status/{ID}/{status}")
    public ApiResponse updateStatus(@PathVariable int ID, @PathVariable boolean status) {
        tasks.get(ID).setStatus(status);

        return new ApiResponse("Task status updated successfully");
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
