package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.BookMapper;
import lt.eimantas.eshop.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookMapper bookMapper;

    @GetMapping("all")
    public List<Book> getAll() {
        return bookMapper.findAll();
    }

}
