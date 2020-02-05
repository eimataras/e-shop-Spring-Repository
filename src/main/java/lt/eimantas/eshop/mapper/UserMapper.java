package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `User`")
    List<User> findAll();

    @Select("SELECT * FROM `User` WHERE `user_id`=#{user_id}")
    Optional<User> findById(Integer user_id);

    @Insert("INSERT INTO `User` (`name`, `surname`, `username`, `password`, `role`) VALUES (#{name}, #{surname}, #{username}, #{password}, #{role})")
//    @SelectKey(statement = "SELECT SCOPE_IDENTITY()", keyProperty = "id", before = false, resultType = Integer.class)
    void add(User user);

    @Update("UPDATE `User` SET `name`=#{name}, `surname`=#{surname}, `username`=#{username}, `password`=#{password}, `role`=#{role} WHERE `user_id`=#{user_id}")
    void update(User user);

    //Slaptazodzio keitimas ivedus username ir sena slaptazodi
    @Update("UPDATE `User` SET `password`=#{newPassword} WHERE `username`=#{username} AND `password`=#{oldPassword}")
    void changePassword(String username, String oldPassword, String newPassword );

    @Delete("DELETE FROM `User` WHERE `user_id` = #{user_id}")
    void deleteById(Integer user_id);
}