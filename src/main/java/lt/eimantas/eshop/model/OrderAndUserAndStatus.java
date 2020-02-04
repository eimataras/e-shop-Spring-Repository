package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class OrderAndUserAndStatus {
    private Integer order_id;
    private Integer user_id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String role;
    private Integer status_id;
    private String type;
}