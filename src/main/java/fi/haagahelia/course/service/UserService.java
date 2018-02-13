package fi.haagahelia.course.service;

import fi.haagahelia.course.model.User;

import java.util.List;

public interface UserService {
    boolean registerUser(User user);
    User getCurrentUser();
    List<User> findUsers(String fullname);
}
