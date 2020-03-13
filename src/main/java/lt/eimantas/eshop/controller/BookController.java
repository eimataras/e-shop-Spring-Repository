package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.BookMapper;
import lt.eimantas.eshop.model.Book;
import lt.eimantas.eshop.model.User;
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
    public @ResponseBody
    Optional<Book> getOneBookById(@RequestParam Integer book_id) {
        return bookMapper.findById(book_id);
    }

    @GetMapping("/max-id")
    public @ResponseBody Optional <Book> getBookByMaxId(){
        return bookMapper.findByMaxId();
    }

    @PostMapping("/add")
    public @ResponseBody Optional <Book> addBook(@RequestBody Book book) {
        bookMapper.add(book);
        return bookMapper.findByMaxId();
    }

    @PutMapping("/edit")
    public void updateBook(@RequestBody Book book) {
        bookMapper.update(book);
    }

    @DeleteMapping("/delete")
    public @ResponseBody Optional<Book> deleteBook(@RequestParam Integer book_id) {
        Optional<Book> result = bookMapper.findById(book_id);
        bookMapper.deleteById(book_id);
        return result;
    }
}
