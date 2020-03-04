package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {


    @Select("SELECT * FROM `User`")
    @Results({
            @Result(id = true, property = "user_id", column = "user_id"),
            @Result(property = "roles", column = "user_id",
                    many = @Many(select = "lt.eimantas.eshop.mapper.UserRoleMapper.findByUserId"))
    })
    List<User> findAll();


    @Select("SELECT * FROM `User` WHERE `user_id`=#{user_id}")
    @Results({
            @Result(id = true, property = "user_id", column = "user_id"),
            @Result(property = "roles", column = "user_id",
                    many = @Many(select = "lt.eimantas.eshop.mapper.UserRoleMapper.findByUserId"))
    })
    Optional<User> findById(Integer user_id);


    @Insert("INSERT INTO `User` (`name`, `surname`, `username`, `password`) VALUES (#{name}, #{surname}, #{username}, #{password})")
//    @SelectKey(statement = "SELECT SCOPE_IDENTITY()", keyProperty = "id", before = false, resultType = Integer.class)
    void add(User user);


    @Select("SELECT * FROM `User` ORDER BY user_id DESC LIMIT 1")
    @Results({
            @Result(id = true, property = "user_id", column = "user_id"),
            @Result(property = "roles", column = "user_id",
                    many = @Many(select = "lt.eimantas.eshop.mapper.UserRoleMapper.findByUserId"))
    })
    Optional<User> findByMaxId();


    @Select("SELECT user_id FROM `User` ORDER BY user_id DESC LIMIT 1")
    Integer findMaxId();


    @Update("UPDATE `User` SET `name`=#{name}, `surname`=#{surname}, `username`=#{username}, `password`=#{password} WHERE `user_id`=#{user_id}")
    void update(User user);


    //Slaptazodzio keitimas ivedus username ir sena slaptazodi
    @Update("UPDATE `User` SET `password`=#{newPassword} WHERE `username`=#{username} AND `password`=#{oldPassword}")
    void changePassword(String username, String oldPassword, String newPassword);


    @Delete("DELETE FROM `User` WHERE `user_id` = #{user_id}")
    void deleteById(Integer user_id);
}