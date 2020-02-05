package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class OrderItemsAndBook {
    private Integer order_item_id;   //(PK)
    private Integer order_id;   //(FK)
    private Integer book_id;   //(FK)
    private String title;
    private String author;
    private String published_date;
    private String book_cover; //image
    private Integer quantity;
}
