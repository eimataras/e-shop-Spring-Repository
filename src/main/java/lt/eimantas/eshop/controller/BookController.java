package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.BookMapper;
import lt.eimantas.eshop.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;


    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookMapper.findAll();
    }

    @GetMapping("/:id")
    public @ResponseBody
    Optional<Book> getOneBookById(@RequestParam Integer book_id) {
        Optional<Book> result = bookMapper.findById(book_id);
        return result;
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
//        Book book = new Book();
//        book.setTitle("Pasakos vaikams");
//        book.setAuthor("Kazys Binkis");
//        book.setPublished_date("2010-01-01");
//        book.setBook_cover("https://...");
//        book.setQuantity(5);
        bookMapper.add(book);
    }

    @PutMapping("/edit")
    public void updateBook(@RequestBody Book book) {
        bookMapper.update(book);
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam Integer book_id) {
        bookMapper.deleteById(book_id);
    }
}
