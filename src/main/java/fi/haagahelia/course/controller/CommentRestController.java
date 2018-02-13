package fi.haagahelia.course.controller;

import fi.haagahelia.course.model.Comment;
import fi.haagahelia.course.model.Task;
import fi.haagahelia.course.service.CommentService;
import fi.haagahelia.course.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController(value = "/api/comments")
public class CommentRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/api/comments/{id}")
    public ResponseEntity<Set<Comment>> getComments(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task.getComments());
    }

    @PostMapping(value = "/api/comment/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        Comment comment = commentService.findById(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping(value = "/api/comment/save")
    public ResponseEntity<?> saveComment(@RequestBody Comment comment){
        commentService.save(comment);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "api/comment/update")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment){
        commentService.updateComment(comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/api/comment/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
