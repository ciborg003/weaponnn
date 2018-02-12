package fi.haagahelia.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by brief on 09.02.2018.
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"tasks", "manager", "developers"})
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "id_project")
    private Long id;
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Manager_id",nullable = false)
    private User manager;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinTable(name = "project_has_user",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "developer_id",referencedColumnName = "id_user")
    )
    @JsonIgnore
    private Set<User> developers = new HashSet<>();
}
