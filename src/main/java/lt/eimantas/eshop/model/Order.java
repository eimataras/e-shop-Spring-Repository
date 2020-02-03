package lt.eimantas.eshop.model;

import lombok.Data;

@Data   //Creates constructor, getters and setter when compiling
public class Order {
    private Integer order_id;   //(PK)
    private Integer user_id;   //(FK)
    private Integer status_id;   //(FK)
}
