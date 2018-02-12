package fi.haagahelia.course.service.Impl;

import fi.haagahelia.course.model.Project;
import fi.haagahelia.course.repository.ProjectRepository;
import fi.haagahelia.course.service.ProjectService;
import fi.haagahelia.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;

    @Override
    public Project save(Project project) {
        project.setManager(userService.getCurrentUser());
        return projectRepository.save(project);
    }
}
