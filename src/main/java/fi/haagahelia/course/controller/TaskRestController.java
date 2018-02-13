package fi.haagahelia.course.controller;

import fi.haagahelia.course.model.Project;
import fi.haagahelia.course.model.Task;
import fi.haagahelia.course.service.ProjectService;
import fi.haagahelia.course.service.TaskService;
import fi.haagahelia.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController(value = "/api/tasks")
public class TaskRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/api/tasks/{id}")
    public ResponseEntity<Set<Task>> getTasks(@PathVariable Long id) {
        Project project = projectService.findById(id);
        return ResponseEntity.ok(project.getTasks());
    }

    @GetMapping(value = "/api/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping(value = "/api/task/save")
    public ResponseEntity<?> saveTask(@RequestBody Task task){
        taskService.save(task);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "api/task/status")
//    public ResponseEntity<Task> updateStatus(@RequestParam("id_task") Long id, @RequestParam("status") String status){
    public ResponseEntity<Task> updateStatus(@RequestBody Task newTask){
        Task task = taskService.updateTaskStatus(newTask.getId(), newTask.getStatus());
        return ResponseEntity.ok(task);
    }
}
