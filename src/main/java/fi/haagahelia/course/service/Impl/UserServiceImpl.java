package fi.haagahelia.course.service.Impl;

import fi.haagahelia.course.model.Project;
import fi.haagahelia.course.model.User;
import fi.haagahelia.course.repository.ProjectRepository;
import fi.haagahelia.course.repository.UserRepository;
import fi.haagahelia.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

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
        if (names[0] == null) {
            return new ArrayList<>();
        }
        if(names.length == 1) {
            return userRepository.findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingAndRole_Role(names[0], names[0], "ROLE_DEVELOPER");
        }
        return userRepository.findAllByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingAndRole_Role(names[0], names[1], "ROLE_DEVELOPER");
    }

    @Override
    public List<User> findDevs() {
        return userRepository.findAllByRole_Role("ROLE_DEVELOPER");
    }

    @Override
    public Set<User> findDevsByProj(Long id) {
        Project project = projectRepository.findOne(id);
        Set<User> users = project.getDevelopers();
        return users;
    }
}
