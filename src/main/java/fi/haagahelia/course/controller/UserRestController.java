package fi.haagahelia.course.controller;

import fi.haagahelia.course.model.User;
import fi.haagahelia.course.repository.UserRepository;
import fi.haagahelia.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(name = "/api/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserService userService;

    @GetMapping("/api/user")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    @PostMapping("api/user/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        if (user != null && userService.registerUser(user)) {
            return ResponseEntity.ok(null);
        }

        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @GetMapping("/api/user/search")
    public ResponseEntity<List<User>> findUsers(@RequestParam("Fullname") String fullname) {
        List<User> users = userService.findUsers(fullname);
        return ResponseEntity.ok(users);
    }
}