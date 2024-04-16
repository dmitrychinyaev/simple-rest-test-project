package ru.dmitrychinyaev.taskapi.controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.dmitrychinyaev.taskapi.controller.TaskController;
import ru.dmitrychinyaev.taskapi.entity.Task;
import ru.dmitrychinyaev.taskapi.service.TaskService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ControllerTests {
    @Mock
    TaskService taskService;

    @InjectMocks
    TaskController taskController;

    @Test
    void testFindAll() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Task task1 = new Task(1, "Test1", "Description for test1", formatter.parse("12.08.1993"));
        Task task2 = new Task(2, "Test2", "Description for test2", formatter.parse("12.08.1993"));
        List<Task> testTasks = Arrays.asList(task1, task2);

        Mockito.when(taskService.taskList()).thenReturn(testTasks);

        var responseEntity = this.taskController.allTask();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testTasks, responseEntity.getBody());
    }

    @Test
    void handleCreateNewTask() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Task task3 = new Task(3, "Test3", "Description for test3", formatter.parse("12.08.1993"));

        var responseEntity = this.taskController.saveTask(task3);
        var responseEntityBody = responseEntity.getBody();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntityBody.isCompleted());

        verify(this.taskService).saveNewTask(task3);
    }
}
