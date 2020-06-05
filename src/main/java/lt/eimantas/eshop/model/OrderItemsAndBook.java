package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class OrderItemsAndBook {
    private Integer orderItemId;   //(PK)
    private Integer orderId;   //(FK)
    private Integer bookId;   //(FK)
    private String title;
    private String author;
    private String publishedDate;
    private String bookCover; //image
    private Integer quantity;
}
