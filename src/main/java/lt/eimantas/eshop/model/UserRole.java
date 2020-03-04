package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class UserRole {

    private Integer user_role_id;
    private Integer user_id;
    private Integer role_id;
    private String role_name; //Recently added


    public UserRole(Integer user_id, Integer role_id, String role_name) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.role_name = role_name;
    }
}
