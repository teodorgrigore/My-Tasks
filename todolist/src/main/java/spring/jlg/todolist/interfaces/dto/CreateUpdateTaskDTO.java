package spring.jlg.todolist.interfaces.dto;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CreateUpdateTaskDTO {
    private String title;
    private String description;
    private LocalDateTime dueDate;
}
