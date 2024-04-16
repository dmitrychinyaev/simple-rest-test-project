package ru.dmitrychinyaev.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dmitrychinyaev.taskapi.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM tasks_test WHERE completed = FALSE; ", nativeQuery = true)
    List<Task> listCurrentTasks();
}
