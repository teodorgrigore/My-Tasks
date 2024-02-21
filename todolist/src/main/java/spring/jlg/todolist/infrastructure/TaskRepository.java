package spring.jlg.todolist.infrastructure;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import spring.jlg.todolist.domain.Task;

import java.util.List;

@Repository
public class TaskRepository {

    private MongoTemplate mongoTemplate;

    public TaskRepository(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(final Task task) {
        this.mongoTemplate.save(task);
    }

    public List<Task> findAll() {
        return this.mongoTemplate.findAll(Task.class);
    }

    public Task findById(final String taskId) {
        return this.mongoTemplate.findById(taskId, Task.class);
    }

    public void delete(final String taskId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(taskId));
        this.mongoTemplate.remove(query, Task.class);
    }
}
