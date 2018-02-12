package fi.haagahelia.course.repository;

import fi.haagahelia.course.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findById(Long id);
    User findByFirstNameAndLastName(String firstname, String lastname);
}
