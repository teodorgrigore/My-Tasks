package spring.jlg.todolist.interfaces.dto;

import lombok.Getter;
import spring.jlg.todolist.domain.TaskStatus;
@Getter
public class ChangeStatusDTO {
    private String id;
    private String newStatus;
}
