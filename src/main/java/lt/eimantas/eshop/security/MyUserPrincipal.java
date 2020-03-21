package lt.eimantas.eshop.security;

import lt.eimantas.eshop.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserPrincipal extends User implements UserDetails {

    public MyUserPrincipal (final User user) {
        super(user);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole_name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    private final User user;
//
//
//    public MyUserPrincipal(User user) {
//        this.user = user;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    // {noop}password pavercia String password suprantamu uzkoduotam DelegatingPasswordEncoder
//    @Override
//    public String getPassword() {
//        String result = "{noop}";
//        return result+user.getPassword();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("User"));
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}