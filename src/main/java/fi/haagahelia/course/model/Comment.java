package fi.haagahelia.course.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by brief on 09.02.2018.
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "task"})
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id_comment")
    private Long id;
    @Column(name = "Text")
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_user", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id_task", nullable = false)
    private Task task;
}
