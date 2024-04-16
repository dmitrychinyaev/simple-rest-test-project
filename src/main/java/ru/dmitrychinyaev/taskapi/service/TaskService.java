package ru.dmitrychinyaev.taskapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitrychinyaev.taskapi.entity.Task;
import ru.dmitrychinyaev.taskapi.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional
    public void saveNewTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    @CachePut(value = "tasksCache", key = "#title")
    public List<Task> taskList() {
        return taskRepository.findAll();
    }

    @Transactional
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public void changeTask(Task task, Long id) {
        Task currentTask = findById(id).orElseThrow(RuntimeException::new);
        currentTask.setTitle(task.getTitle());
        currentTask.setDescription(task.getDescription());
        currentTask.setDueDate(task.getDueDate());
        currentTask.setCompleted(task.isCompleted());
        saveNewTask(currentTask);
    }

    @Transactional
    @CacheEvict(value = "tasksCache")
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public List<Task> taskCurrentList() {
        return taskRepository.listCurrentTasks();
    }
}
