package fi.haagahelia.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode(exclude = {"project", "user"})
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "id_task")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Status")
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id_project", nullable = false)
    @JsonIgnore
    private Project project;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_user", nullable = false)
    @JsonIgnore
    private User user;
}
