package spring.jlg.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Document
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private TaskStatus status;
    private LocalDateTime dueDate;

    public void update(final String title, final String description, final LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public void changeStatus(final TaskStatus newStatus) {
        this.status = newStatus;
    }
}
