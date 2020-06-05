package lt.eimantas.eshop.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderAndUserAndStatus {
    private Integer orderId;
    private Integer userId;
    private String name;
    private String surname;
    private String username;
    private String password;
//    private String role;
    private Integer statusId;
    private String type;
    private List<OrderItemsAndBook> items;
}
