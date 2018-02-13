package fi.haagahelia.course.controller;

import fi.haagahelia.course.model.Project;
import fi.haagahelia.course.model.Task;
import fi.haagahelia.course.model.TaskProjIdDTO;
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
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/api/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping(value = "/api/task/save")
    public ResponseEntity<?> saveTask(@RequestBody TaskProjIdDTO task){
        Task newTask = task.getTask();
        newTask.setProject(projectService.findById(task.getProjId()));
        taskService.save(newTask);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "api/task/status")
//    public ResponseEntity<Task> updateStatus(@RequestParam("id_task") Long id, @RequestParam("status") String status){
    public ResponseEntity<Task> updateStatus(@RequestBody Task newTask){
        Task task = taskService.updateTaskStatus(newTask.getId(), newTask.getStatus());
        return ResponseEntity.ok(task);
    }
}
