package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class OrderAndUserAndStatusAndItemsAndBook {
    private Integer orderId;
//    private Integer userId;
    private String name;
    private String surname;
    private String username;
//    private String password;
//    private String role;
//    private Integer statusId;
    private String type;
//    private Integer orderItemId;
//    private Integer bookId;
    private String title;
    private String author;
    private String publishedDate;
    private String bookCover;
    private Integer quantity;
}
