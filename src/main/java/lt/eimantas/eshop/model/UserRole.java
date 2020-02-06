package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class UserRole {

    private Integer user_role_id;
    private Integer user_id;
    private Integer role_id;
}
