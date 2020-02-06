package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.UserRoleMapper;
import lt.eimantas.eshop.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleMapper userRoleMapper;


    @GetMapping("/all")
    public List<UserRole> getAllUsersRoles() {
        return userRoleMapper.findAll();
    }

    @GetMapping("/:id")
    public @ResponseBody
    Optional<UserRole> getOneUserRoleById(@RequestParam Integer user_role_id) {
        Optional<UserRole> result = userRoleMapper.findById(user_role_id);
        return result;
    }

    @PostMapping("/add")
    public void addUserRole(@RequestBody UserRole userRole) {
        userRoleMapper.add(userRole);
    }

    @PutMapping("/edit")
    public void updateUserRole(@RequestBody UserRole userRole) {
        userRoleMapper.update(userRole);
    }

    @DeleteMapping("/delete")
    public void deleteUserRole(@RequestParam Integer user_role_id) {
        userRoleMapper.deleteById(user_role_id);
    }
}