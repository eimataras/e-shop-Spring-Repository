package lt.eimantas.eshop.model;

import lombok.Data;

import java.util.List;

@Data   //Creates constructor, getters and setter when compiling
public class Order {
    private Integer orderId;   //(PK)
    private Integer userId;   //(FK)
    private Integer statusId; //(FK)

    private Integer bookId; //Duombazej neegzistuoja, reikalingas tik del auto addOrderItem vykdymo
}
