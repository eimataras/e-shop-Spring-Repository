package lt.eimantas.eshop.model;

import lombok.*;

@Data
public class OrderItems {
    private Integer orderItemId;   //(PK)
    private Integer orderId;   //(FK)
    private Integer bookId;   //(FK)
    private Integer quantity;

    public OrderItems() {}

    public OrderItems(Integer orderId, Integer bookId, Integer quantity) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public OrderItems(Integer optionalOrderItemId, Integer orderId, Integer bookId, Integer quantity) {
        this.orderItemId = optionalOrderItemId;
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
