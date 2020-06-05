package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class UserRole {

    private Integer userRoleId;
    private Integer userId;
    private Integer roleId;
    private String roleName; //Recently added


    public UserRole(Integer userId, Integer roleId, String roleName) {
        this.userId = userId;
        this.roleId = roleId;
        this.roleName = roleName;
    }
}