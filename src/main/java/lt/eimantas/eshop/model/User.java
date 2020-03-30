package lt.eimantas.eshop.model;

import lombok.Data;
import java.util.List;

@Data   //Creates getters and setter when compiling
public class User {

    private Integer user_id;
    private String name;
    private String surname;
    private String username;   //(UK)
    private String password;
    private List<UserRole> roles;

    public User() {
    }

    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public User(User user) {
        this.roles = user.getRoles();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.user_id = user.getUser_id();
    }
}