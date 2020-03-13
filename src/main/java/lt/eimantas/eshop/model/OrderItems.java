package lt.eimantas.eshop.model;

import lombok.*;

@Data
public class OrderItems {
    private Integer order_item_id;   //(PK)
    private Integer order_id;   //(FK)
    private Integer book_id;   //(FK)
    private Integer quantity;

    public OrderItems() {}

    public OrderItems(Integer order_id, Integer book_id, Integer quantity) {
        this.order_id = order_id;
        this.book_id = book_id;
        this.quantity = quantity;
    }

    public OrderItems(Integer optionalOrderItemId, Integer order_id, Integer book_id, Integer quantity) {
        this.order_item_id = optionalOrderItemId;
        this.order_id = order_id;
        this.book_id = book_id;
        this.quantity = quantity;
    }
}
