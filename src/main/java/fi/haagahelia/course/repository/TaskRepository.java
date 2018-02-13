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
    @Query("UPDATE Task t set t.status=?1 where t.id=?2")
    void updateTaskStatus(@Param("status") String status, @Param("id") Long id);
}
