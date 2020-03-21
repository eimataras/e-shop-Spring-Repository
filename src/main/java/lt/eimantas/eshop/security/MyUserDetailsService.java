package lt.eimantas.eshop.security;

import lt.eimantas.eshop.mapper.UserMapper;
import lt.eimantas.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = userMapper.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        return optionalUser.map(MyUserPrincipal::new).get();
    }
}