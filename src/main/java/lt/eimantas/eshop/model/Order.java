package lt.eimantas.eshop.model;

import lombok.Data;

import java.util.List;

@Data   //Creates constructor, getters and setter when compiling
public class Order {
    private Integer order_id;   //(PK)
    private Integer user_id;   //(FK)
    private Integer status_id; //(FK)

    private Integer book_id; //Duombazej neegzistuoja, reikalingas tik del auto addOrderItem vykdymo
}
