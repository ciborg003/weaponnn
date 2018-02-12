package fi.haagahelia.course.service.Impl;

import fi.haagahelia.course.model.Task;
import fi.haagahelia.course.repository.ProjectRepository;
import fi.haagahelia.course.repository.TaskRepository;
import fi.haagahelia.course.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Task save(Task task) {
        task.setProject(projectRepository.findOne(task.getProject().getId()));
        return taskRepository.save(task);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Task updateTaskStatus(Long id, String status) {
        taskRepository.updateTaskStatus(id, status);
        return taskRepository.findOne(id);
    }
}
