package fi.haagahelia.course.service.Impl;

import fi.haagahelia.course.model.User;
import fi.haagahelia.course.repository.UserRepository;
import fi.haagahelia.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail())!=null) {
            return false;
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public List<User> findUsers(String fullname) {
        String[] names = fullname.split(" ", 2);
        return userRepository.findAllByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingAndRole_Role(names[0], names[1], "Developer");
    }
}
