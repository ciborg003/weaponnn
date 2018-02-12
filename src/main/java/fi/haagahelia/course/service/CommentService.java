package fi.haagahelia.course.service;

import fi.haagahelia.course.model.Comment;

public interface CommentService {
    Comment findById(Long id);
    Comment save(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long id);
}
