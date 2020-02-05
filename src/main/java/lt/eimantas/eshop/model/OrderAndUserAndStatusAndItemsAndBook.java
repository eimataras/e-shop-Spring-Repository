package lt.eimantas.eshop.model;

import lombok.Data;

@Data
public class OrderAndUserAndStatusAndItemsAndBook {
    private Integer order_id;
//    private Integer user_id;
    private String name;
    private String surname;
    private String username;
//    private String password;
//    private String role;
//    private Integer status_id;
    private String type;
//    private Integer order_item_id;
//    private Integer book_id;
    private String title;
    private String author;
    private String published_date;
    private String book_cover;
    private Integer quantity;
}
