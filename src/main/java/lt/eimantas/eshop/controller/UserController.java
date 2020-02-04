package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.BookMapper;
import lt.eimantas.eshop.mapper.UserMapper;
import lt.eimantas.eshop.model.Book;
import lt.eimantas.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @GetMapping("/:id")
    public @ResponseBody
    Optional<User> getOneUserById(@RequestParam Integer user_id) {
        Optional<User> result = userMapper.findById(user_id);
        return result;
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userMapper.add(user);
    }

    @PutMapping("/edit")
    public void updateUser(@RequestBody User user) {
        userMapper.update(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Integer user_id) {
        userMapper.deleteById(user_id);
    }
}
