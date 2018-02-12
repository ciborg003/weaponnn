package fi.haagahelia.course.service;

import fi.haagahelia.course.model.Task;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    Task save(Task task);
}
