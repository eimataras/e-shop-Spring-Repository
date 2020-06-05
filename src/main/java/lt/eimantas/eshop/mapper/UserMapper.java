package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {


    @Select("SELECT * FROM `User`")
    @Results({
            @Result(id = true, property = "userId", column = "userId"),
            @Result(property = "roles", column = "userId",
                    many = @Many(select = "lt.eimantas.eshop.mapper.UserRoleMapper.findByUserId"))
    })
    List<User> findAll();


    @Select("SELECT * FROM `User` WHERE `userId`=#{userId}")
    @Results({
            @Result(id = true, property = "userId", column = "userId"),
            @Result(property = "roles", column = "userId",
                    many = @Many(select = "lt.eimantas.eshop.mapper.UserRoleMapper.findByUserId"))
    })
    Optional<User> findById(Integer userId);


    @Select("SELECT * FROM `User` WHERE `username`=#{username}")
    @Results({
            @Result(id = true, property = "userId", column = "userId"),
            @Result(property = "roles", column = "userId",
                    many = @Many(select = "lt.eimantas.eshop.mapper.UserRoleMapper.findByUserId"))
    })
    User findByUsername(String username);


    @Select("SELECT * FROM `User` WHERE `uid`=#{uid}")
    @Results({
            @Result(id = true, property = "userId", column = "userId"),
            @Result(property = "roles", column = "userId",
                    many = @Many(select = "lt.eimantas.eshop.mapper.UserRoleMapper.findByUserId"))
    })
    User findByUid(String uid);


    @Insert("INSERT INTO `User` (`name`, `surname`, `username`, `password`, `uid`) VALUES (#{name}, #{surname}, #{username}, #{password}, #{uid})")
    void add(User user);


    @Select("SELECT * FROM `User` ORDER BY userId DESC LIMIT 1")
    @Results({
            @Result(id = true, property = "userId", column = "userId"),
            @Result(property = "roles", column = "userId",
                    many = @Many(select = "lt.eimantas.eshop.mapper.UserRoleMapper.findByUserId"))
    })
    Optional<User> findByMaxId();


    @Select("SELECT userId FROM `User` ORDER BY userId DESC LIMIT 1")
    Integer findMaxId();


    @Update("UPDATE `User` SET `name`=#{name}, `surname`=#{surname}, `username`=#{username}, `password`=#{password}, `uid`=#{uid} WHERE `userId`=#{userId}")
    void update(User user);


    //Slaptazodzio keitimas ivedus username ir sena slaptazodi
    @Update("UPDATE `User` SET `password`=#{newPassword} WHERE `username`=#{username} AND `password`=#{oldPassword}")
    void changePassword(String username, String oldPassword, String newPassword);


    @Delete("DELETE FROM `User` WHERE `userId` = #{userId}")
    void deleteById(Integer userId);
}