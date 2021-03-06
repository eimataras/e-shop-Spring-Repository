package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.UserMapper;
import lt.eimantas.eshop.mapper.UserRoleMapper;
import lt.eimantas.eshop.model.User;
import lt.eimantas.eshop.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @GetMapping("/:id")
    public Optional<User> getUserById(@RequestParam Integer id) {
        return userMapper.findById(id);
    }

    @GetMapping("/max-id")
    public Optional<User> getUserByMaxId() {
        return userMapper.findByMaxId();
    }

    @GetMapping("/username")
    public User getUserByUsername(@RequestParam String username) {
        return userMapper.findByUsername(username);
    }

    @PostMapping("/add-client")
    public Optional<User> addUserClient(@RequestBody User user) {
        User encodedUser = new User(user.getName(), user.getSurname(), user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getUid());
        userMapper.add(encodedUser);

        //---------SUKURIA IRASA (NEW CLIENT) UserRole LENTELEJE-------------
        Integer userId = userMapper.findMaxId();
        Integer roleId = 2;  // CLIENT
        String roleName = "CLIENT - autoMagic";
        UserRole userRole = new UserRole(userId, roleId, roleName);
        userRoleMapper.add(userRole);
        //---------SUKURIA IRASA (NEW CLIENT) UserRole LENTELEJE-------------

        return userMapper.findByMaxId();
    }

    @PostMapping("/add-admin")
    public Optional<User> addUserAdmin(@RequestBody User user) {
        User encodedUser = new User(user.getName(), user.getSurname(), user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getUid());
        userMapper.add(encodedUser);

        //---------SUKURIA IRASA (NEW ADMIN) UserRole LENTELEJE-------------
        Integer userId = userMapper.findMaxId();
        Integer roleId = 1;  // ADMIN
        String roleName = "ADMIN - autoMagic";
        UserRole userRole = new UserRole(userId, roleId, roleName);
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
    public Optional<User> deleteUser(@RequestParam Integer userId) {
        Optional<User> result = userMapper.findById(userId);
        userMapper.deleteById(userId);
        return result;
    }
}