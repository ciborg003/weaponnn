package fi.haagahelia.course.service.Impl;

import fi.haagahelia.course.model.Comment;
import fi.haagahelia.course.repository.CommentRepository;
import fi.haagahelia.course.repository.TaskRepository;
import fi.haagahelia.course.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment findById(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public Comment save(Comment comment) {
        comment.setTask(taskRepository.findOne(comment.getTask().getId()));
        return commentRepository.save(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }
}
