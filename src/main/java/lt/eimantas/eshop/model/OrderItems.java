package lt.eimantas.eshop.model;

import lombok.Data;

@Data   //Creates constructor, getters and setter when compiling
public class OrderItems {
    private Integer order_item_id;   //(PK)
    private Integer order_id;   //(FK)
    private Integer book_id;   //(FK)
    private Integer quantity;
}
