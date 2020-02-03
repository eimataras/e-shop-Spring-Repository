package lt.eimantas.eshop.model;

import lombok.Data;

@Data   //Creates constructor, getters and setter when compiling
public class OrderStatus {
    private Integer status_id;   //(PK)
    private String type;   //(1-new, 2-paid, 3-sent, 4-cancelled)
}
