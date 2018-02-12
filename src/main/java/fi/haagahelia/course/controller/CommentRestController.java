package fi.haagahelia.course.controller;

import fi.haagahelia.course.model.Comment;
import fi.haagahelia.course.model.Task;
import fi.haagahelia.course.service.CommentService;
import fi.haagahelia.course.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController(value = "/api/comments")
public class CommentRestController {

    @Autowired
    private TaskService taskService;

    /*@GetMapping(value = "/api/tasks/{id}")
    public ResponseEntity<Set<Comment>> getComments(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task.getComments());
    }*/
}
