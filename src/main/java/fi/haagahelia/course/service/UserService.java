package fi.haagahelia.course.service;

import fi.haagahelia.course.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean registerUser(User user);
    User getCurrentUser();
    List<User> findUsers(String fullname);
    List<User> findDevs();
    Set<User> findDevsByProj(Long id);
}
