package ru.dmitrychinyaev.taskapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks_test")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Поле должно быть заполнено")
    @Size(min = 2, max = 15, message = "Поле должно включать от 2 до 15 символов")
    private String title;

    @NotBlank(message = "Поле должно быть заполнено")
    @Size(min = 2, max = 15, message = "Поле должно включать от 2 до 15 символов")
    private String description;

    @NotNull
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date dueDate;

    private boolean completed;

    //конструктор для создания новой задачи, чтобы при создании completed был false.
    public Task(long id, String title, String description, Date dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }
}
