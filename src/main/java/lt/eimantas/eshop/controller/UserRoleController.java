package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.UserRoleMapper;
import lt.eimantas.eshop.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/user-role")
public class UserRoleController {

    @Autowired
    private UserRoleMapper userRoleMapper;


    @GetMapping("/all")
    public List<UserRole> getAllUsersRoles() {
        return userRoleMapper.findAll();
    }

    @GetMapping("/:id")
    public Optional<UserRole> getOneUserRoleById(@RequestParam Integer userRoleId) {
        return userRoleMapper.findById(userRoleId);
    }

    @GetMapping("/max-id")
    public Optional<UserRole> getUserRoleByMaxId() {
        return userRoleMapper.findByMaxId();
    }

    @PostMapping("/add")
    public Optional<UserRole> addUserRole(@RequestBody UserRole userRole) {
        userRoleMapper.add(userRole);
        return userRoleMapper.findByMaxId();
    }

    @PutMapping("/edit")
    public void updateUserRole(@RequestBody UserRole userRole) {
        userRoleMapper.update(userRole);
    }

    @DeleteMapping("/delete")
    public void deleteUserRole(@RequestParam Integer userRoleId) {
        userRoleMapper.deleteById(userRoleId);
    }
}