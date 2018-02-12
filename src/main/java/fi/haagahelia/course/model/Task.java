package fi.haagahelia.course.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by brief on 09.02.2018.
 */
@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "idTask")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Status")
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Project_idProject", nullable = false)
    private Project project;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idUser", nullable = false)
    private User user;
}
