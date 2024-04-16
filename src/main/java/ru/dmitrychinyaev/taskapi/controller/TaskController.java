package ru.dmitrychinyaev.taskapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dmitrychinyaev.taskapi.entity.Task;
import ru.dmitrychinyaev.taskapi.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping()
    public ResponseEntity<Task> saveTask(@RequestBody @Valid Task task) {
        taskService.saveNewTask(task);
        return ResponseEntity.ok(task);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> allTask() {
        return ResponseEntity.ok(taskService.taskList());
    }

    @GetMapping("/current")
    public ResponseEntity<List<Task>> allTaskNoCompleted() {
        return ResponseEntity.ok(taskService.taskCurrentList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task taskToFind = taskService.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(taskToFind);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.changeTask(task, id);
        return ResponseEntity.ok("task changed");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
