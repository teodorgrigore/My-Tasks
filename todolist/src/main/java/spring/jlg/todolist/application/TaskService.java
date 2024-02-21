package spring.jlg.todolist.application;

import org.springframework.stereotype.Service;
import spring.jlg.todolist.domain.Task;
import spring.jlg.todolist.domain.TaskStatus;
import spring.jlg.todolist.infrastructure.TaskRepository;
import spring.jlg.todolist.interfaces.dto.ChangeStatusDTO;
import spring.jlg.todolist.interfaces.dto.CreateUpdateTaskDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void create(final CreateUpdateTaskDTO dto) {
        final Task task = new Task(
                UUID.randomUUID().toString(),
                dto.getTitle(),
                dto.getDescription(),
                LocalDateTime.now(),
                TaskStatus.PENDING,
                dto.getDueDate()
                );

        this.taskRepository.save(task);
    }

    public List<Task> getAll() {
        return this.taskRepository.findAll();
    }

    public void update(final String taskId, final CreateUpdateTaskDTO dto) {
        final Task task = this.taskRepository.findById(taskId);
        task.update(dto.getTitle(), dto.getDescription(), dto.getDueDate());
        this.taskRepository.save(task);
    }

    public void delete(String taskId) {
        this.taskRepository.delete(taskId);
    }

    public void changeStatus(final ChangeStatusDTO dto) {
        final Task task = this.taskRepository.findById(dto.getId());
        task.changeStatus(TaskStatus.valueOf(dto.getNewStatus()));
        this.taskRepository.save(task);
    }
}
