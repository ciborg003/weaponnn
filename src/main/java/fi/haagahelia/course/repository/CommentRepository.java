package fi.haagahelia.course.repository;

import fi.haagahelia.course.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
