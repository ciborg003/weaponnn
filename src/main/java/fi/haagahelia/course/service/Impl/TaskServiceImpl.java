package fi.haagahelia.course.service.Impl;

import fi.haagahelia.course.model.Task;
import fi.haagahelia.course.repository.ProjectRepository;
import fi.haagahelia.course.repository.TaskRepository;
import fi.haagahelia.course.repository.UserRepository;
import fi.haagahelia.course.service.TaskService;
import fi.haagahelia.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Task save(Task task, Long projId, Long devId) {
        task.setProject(projectRepository.findOne(projId));
        task.setUser(userRepository.findOne(devId));
        return taskRepository.save(task);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Task updateTaskStatus(Long id, String status) {
        Task task = taskRepository.findOne(id);
        task.setStatus(status);
        taskRepository.save(task);
        return task;
    }
}
