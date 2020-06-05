package lt.eimantas.eshop.model;

import lombok.Data;
import java.util.List;

@Data   //Creates getters and setter when compiling
public class User {

    private Integer userId;
    private String name;
    private String surname;
    private String username;   //(UK)
    private String password;
    private String uid;        //(UK)
    private List<UserRole> roles;

    public User() {
    }

    public User(String name, String surname, String username, String password, String uid) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.uid = uid;
    }

    public User(User user) {
        this.roles = user.getRoles();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.userId = user.getUserId();
        this.uid = user.getUid();
    }
}