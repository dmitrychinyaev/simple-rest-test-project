package ru.dmitrychinyaev.taskapi.serviceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dmitrychinyaev.taskapi.entity.Task;
import ru.dmitrychinyaev.taskapi.repository.TaskRepository;
import ru.dmitrychinyaev.taskapi.service.TaskService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @Test
    void testFindAllTasks() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Task task1 = new Task(1, "Test1", "Description for test1", formatter.parse("12.08.1993"));
        Task task2 = new Task(2, "Test2", "Description for test2", formatter.parse("12.08.1993"));
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        Mockito.when(taskRepository.listCurrentTasks()).thenReturn(tasks);
        List<Task> testList = taskService.taskCurrentList();

        assertEquals(2, testList.size());
        verify(taskRepository, times(1)).listCurrentTasks();
    }

    @Test
    void testSaveNewTask() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Task task1 = new Task(1, "Test1", "Description for test1", formatter.parse("12.08.1993"));

        taskService.saveNewTask(task1);

        verify(taskRepository, times(1)).save(task1);
    }
}
