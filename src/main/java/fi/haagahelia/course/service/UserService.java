package fi.haagahelia.course.service;

import fi.haagahelia.course.model.User;

public interface UserService {
    boolean registerUser(User user);
    User getCurrentUser();
    User findUser(String firstname, String lastname);
}
