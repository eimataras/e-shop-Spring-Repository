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

    //    @Autowired
//    PasswordEncoder encoder;
//
//    private String pass = encoder.encode(password);

}
