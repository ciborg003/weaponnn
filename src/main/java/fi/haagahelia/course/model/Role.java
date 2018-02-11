package fi.haagahelia.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "users")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id_role")
    private Long id;
    @Column(name = "Name")
    private String role;
    @JsonIgnore
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<User>();
}
