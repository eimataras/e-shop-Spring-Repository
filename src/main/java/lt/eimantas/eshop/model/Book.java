package lt.eimantas.eshop.model;

import lombok.Data;

@Data  //Creates constructor, getters and setter when compiling
public class Book {
    private Integer bookId;   //(PK)
    private String title;
    private String author;
    private String publishedDate;
    private String bookCover; //image
    private Integer quantity;
}
