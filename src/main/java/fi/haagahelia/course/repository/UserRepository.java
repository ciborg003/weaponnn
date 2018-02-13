package fi.haagahelia.course.repository;

import fi.haagahelia.course.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findById(Long id);
    List<User> findAllByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingAndRole_Role(String Firstname, String Lastname, String role);
}
