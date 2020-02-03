package lt.eimantas.eshop.model;

import lombok.Data;

@Data  //Creates constructor, getters and setter when compiling
public class Book {
    private Integer book_id;   //(PK)
    private String title;
    private String author;
    private String published_date;
    private String book_cover; //image
    private Integer quantity;
}
