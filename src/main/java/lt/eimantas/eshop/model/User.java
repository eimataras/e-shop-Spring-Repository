package lt.eimantas.eshop.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data   //Creates constructor, getters and setter when compiling
public class User {
    private Integer user_id;
    private String name;
    private String surname;
    private String username;   //(UK)
    private String password;

//    @Autowired
//    PasswordEncoder encoder;
//
//    private String pass = encoder.encode(password);

//    private String role;  // {Admin, Client;}
}
