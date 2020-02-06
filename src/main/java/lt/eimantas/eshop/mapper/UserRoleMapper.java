package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRoleMapper {

    @Select("SELECT * FROM `UserRole`")
    List<UserRole> findAll();

    @Select("SELECT * FROM `UserRole` WHERE `user_role_id`=#{user_role_id}")
    Optional<UserRole> findById(Integer user_role_id);

    @Insert("INSERT INTO `UserRole` (`user_id`, `role_id`) VALUES (#{user_id}, #{role_id})")
    void add(UserRole userRole);

    @Update("UPDATE `UserRole` SET `user_id`=#{user_id}, `role_id`=#{role_id} WHERE `user_role_id`=#{user_role_id}")
    void update(UserRole userRole);

    @Delete("DELETE FROM `UserRole` WHERE `user_role_id` = #{user_role_id}")
    void deleteById(Integer user_role_id);
}
