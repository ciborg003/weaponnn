package fi.haagahelia.course.repository;

import fi.haagahelia.course.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
