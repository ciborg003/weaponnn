package fi.haagahelia.course.controller;

import fi.haagahelia.course.model.Project;
import fi.haagahelia.course.model.User;
import fi.haagahelia.course.service.ProjectService;
import fi.haagahelia.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController(value = "/api/projects")
public class ProjectRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/api/projects")
    public ResponseEntity<Set<Project>> getProjects(){
        User user = userService.getCurrentUser();
        if (user.getRole().getRole().equals("ROLE_MANAGER")){
            return ResponseEntity.ok(user.getManagerProjects());
        }

        return ResponseEntity.ok(user.getDevProjects());
    }

    @PostMapping(value = "/api/projects/save")
    public ResponseEntity<?> saveProject(@RequestBody Project project){
        projectService.save(project);
        return ResponseEntity.ok().build();
    }
}
