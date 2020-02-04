package lt.eimantas.eshop.model;

import lombok.Data;

@Data   //Creates constructor, getters and setter when compiling
public class User {
    private Integer user_id;
    private String name;
    private String surname;
    private String username;   //(UK)
    private String password;
    private String role;  // {Admin, Client;}
}
