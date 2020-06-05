package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.BookMapper;
import lt.eimantas.eshop.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;


    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookMapper.findAll();
    }

    @GetMapping("/:id")
    public Optional<Book> getOneBookById(@RequestParam Integer bookId) {
        return bookMapper.findById(bookId);
    }

    @GetMapping("/max-id")
    public Optional<Book> getBookByMaxId() {
        return bookMapper.findByMaxId();
    }

    @PostMapping("/add")
    public Optional<Book> addBook(@RequestBody Book book) {
        bookMapper.add(book);
        return bookMapper.findByMaxId();
    }

    @PutMapping("/edit")
    public void updateBook(@RequestBody Book book) {
        bookMapper.update(book);
    }

    @DeleteMapping("/delete")
    public Optional<Book> deleteBook(@RequestParam Integer bookId) {
        Optional<Book> result = bookMapper.findById(bookId);
        bookMapper.deleteById(bookId);
        return result;
    }
}
