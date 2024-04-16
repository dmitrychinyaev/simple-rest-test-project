package ru.dmitrychinyaev.taskapi.smoke;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dmitrychinyaev.taskapi.controller.TaskController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private TaskController taskController;

    @Test
    void contextLoads() throws Exception {
        assertThat(taskController).isNotNull();
    }
}
