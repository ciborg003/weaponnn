//package fi.haagahelia.course.service.Impl;
//
//import fi.haagahelia.course.model.User;
//import fi.haagahelia.course.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println(username);
//        System.out.println(username);
//        System.out.println(username);
//        System.out.println(username);
//        System.out.println(username);
//        System.out.println(username);
//        User user = userRepository.findByEmail(username);
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),grantedAuthorities);
//    }
//}
