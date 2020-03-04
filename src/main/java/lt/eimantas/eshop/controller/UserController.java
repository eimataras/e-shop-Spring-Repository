package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.UserMapper;
import lt.eimantas.eshop.mapper.UserRoleMapper;
import lt.eimantas.eshop.model.User;
import lt.eimantas.eshop.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @GetMapping("/:id")
    public @ResponseBody Optional <User> getUserById(@RequestParam Integer id) {
        return userMapper.findById(id);
    }

    @GetMapping("/max-id")
    public @ResponseBody Optional <User> getUserByMaxId(){
        return userMapper.findByMaxId();
    }


    @PostMapping("/add-client")
    public @ResponseBody Optional <User> addUserClient(@RequestBody User user) {
        userMapper.add(user);

        //---------SUKURIA IRASA (NEW CLIENT) UserRole LENTELEJE-------------
        Integer user_id = userMapper.findMaxId();
        Integer role_id = 2;  // CLIENT
        String role_name = "CLIENT - autoMagic";
        UserRole userRole = new UserRole(user_id, role_id, role_name);
        userRoleMapper.add(userRole);
        //---------SUKURIA IRASA (NEW CLIENT) UserRole LENTELEJE-------------

        return userMapper.findByMaxId();
    }


    @PostMapping("/add-admin")
    public @ResponseBody Optional <User> addUserAdmin(@RequestBody User user) {
        userMapper.add(user);

        //---------SUKURIA IRASA (NEW ADMIN) UserRole LENTELEJE-------------
        Integer user_id = userMapper.findMaxId();
        Integer role_id = 1;  // ADMIN
        String role_name = "ADMIN - autoMagic";
        UserRole userRole = new UserRole(user_id, role_id, role_name);
        userRoleMapper.add(userRole);
        //---------SUKURIA IRASA (NEW ADMIN) UserRole LENTELEJE-------------

        return userMapper.findByMaxId();
    }


    @PutMapping("/edit")
    public void updateUser(@RequestBody User user) {
        userMapper.update(user);
    }

    //Slaptazodzio keitimas ivedus username ir sena slaptazodi
    @PutMapping("/changePassword")
    public void changeUserPassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        userMapper.changePassword(username, oldPassword, newPassword);
    }

    @DeleteMapping("/delete")
    public @ResponseBody Optional <User> deleteUser(@RequestParam Integer user_id) {
        Optional <User> result = userMapper.findById(user_id);
        userMapper.deleteById(user_id);
        return result;
    }
}
