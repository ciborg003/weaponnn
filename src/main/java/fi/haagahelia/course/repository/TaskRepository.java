package fi.haagahelia.course.repository;

import fi.haagahelia.course.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Query("UPDATE Task tsk set tsk.status = ?2 where tsk.id = ?1")
    void updateTaskStatus(@Param("id") Long id, @Param("status") String status);
}
