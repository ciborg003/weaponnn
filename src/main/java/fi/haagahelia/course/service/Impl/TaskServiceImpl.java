package fi.haagahelia.course.service.Impl;

import fi.haagahelia.course.model.Task;
import fi.haagahelia.course.repository.TaskRepository;
import fi.haagahelia.course.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }
}
