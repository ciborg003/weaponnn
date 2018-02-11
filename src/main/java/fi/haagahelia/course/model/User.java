package fi.haagahelia.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"role","managerProjects","devProjects"})
@ToString(exclude = {"managerProjects","devProjects","role","password"})
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Long id;
    @Column(name = "Firstname")
    private String firstName;
    @Column(name = "Lastname")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "manager",fetch = FetchType.LAZY)
    private Set<Project> managerProjects = new HashSet<Project>();
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "developers")
    private Set<Project> devProjects = new HashSet<Project>();
}
