package fi.haagahelia.course.controller;

import fi.haagahelia.course.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/comments")
public class CommentRestController {

    @Autowired
    private CommentService commentService;
}
