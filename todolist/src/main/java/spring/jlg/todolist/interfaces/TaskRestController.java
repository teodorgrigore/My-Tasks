package spring.jlg.todolist.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.jlg.todolist.application.TaskService;
import spring.jlg.todolist.domain.Task;
import spring.jlg.todolist.interfaces.dto.ChangeStatusDTO;
import spring.jlg.todolist.interfaces.dto.CreateUpdateTaskDTO;

import java.util.List;

@RestController
@CrossOrigin
public class TaskRestController {

    private TaskService taskService;

    public TaskRestController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Void> create(final @RequestBody CreateUpdateTaskDTO dto) {
        this.taskService.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(this.taskService.getAll());
    }

    @PatchMapping("/tasks/{taskId}")
    public ResponseEntity<Void> update(final @PathVariable String taskId, final @RequestBody CreateUpdateTaskDTO dto) {
        this.taskService.update(taskId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> delete(final @PathVariable String taskId) {
        this.taskService.delete(taskId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tasks/status")
    public ResponseEntity<Void> changeStatus(final @RequestBody ChangeStatusDTO dto) {
        this.taskService.changeStatus(dto);
        return ResponseEntity.ok().build();
    }
}
