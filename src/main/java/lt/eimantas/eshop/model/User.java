package lt.eimantas.eshop.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
//import org.springframework.security.crypto.password.PasswordEncoder; //security uzkomentuotas

@Data   //Creates constructor, getters and setter when compiling
public class User {
    private Integer user_id;
    private String name;
    private String surname;
    private String username;   //(UK)
    private String password;
    private List<UserRole> roles;


    public User () {
    }

    public User (User user) {
        this.roles = user.getRoles();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.user_id = user.getUser_id();
    }

    //    @Autowired
//    PasswordEncoder encoder;
//
//    private String pass = encoder.encode(password);

}
