package fi.haagahelia.course.service;

import fi.haagahelia.course.model.Task;
import org.springframework.stereotype.Service;

public interface TaskService {
    Task save(Task task);
    Task findById(Long id);
    Task updateTaskStatus(Long id_task, String status);
}
