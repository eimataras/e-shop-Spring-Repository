package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.UserRoleMapper;
import lt.eimantas.eshop.model.User;
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
    public @ResponseBody
    Optional<UserRole> getOneUserRoleById(@RequestParam Integer user_role_id) {
        Optional<UserRole> result = userRoleMapper.findById(user_role_id);
        return result;
    }

    @GetMapping("/max-id")
    public @ResponseBody Optional <UserRole> getUserRoleByMaxId(){
        Optional <UserRole> result = userRoleMapper.findByMaxId();

        return result;
    }

    @PostMapping("/add")
    public Optional <UserRole> addUserRole(@RequestBody UserRole userRole) {
        userRoleMapper.add(userRole);

        Optional <UserRole> result = userRoleMapper.findByMaxId();

        return result;
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