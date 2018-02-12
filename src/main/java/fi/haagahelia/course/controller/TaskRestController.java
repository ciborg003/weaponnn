package fi.haagahelia.course.controller;

import fi.haagahelia.course.model.Project;
import fi.haagahelia.course.model.Task;
import fi.haagahelia.course.service.ProjectService;
import fi.haagahelia.course.service.TaskService;
import fi.haagahelia.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/api/tasks")
public class TaskRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    /*@GetMapping(value = "/api/tasts")
    public ResponseEntity<List<Task>> getTasks(Project project) {
    }*/
}
