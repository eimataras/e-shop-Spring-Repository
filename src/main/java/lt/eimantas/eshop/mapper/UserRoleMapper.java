package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRoleMapper {

    @Select("SELECT * FROM `UserRole` LEFT JOIN `Role` ON `UserRole`.`roleId` = `Role`.`roleId`")
    List<UserRole> findAll();

    @Select("SELECT * FROM `UserRole` LEFT JOIN `Role` ON `UserRole`.`roleId` = `Role`.`roleId` WHERE `userRoleId`=#{userRoleId}")
    Optional<UserRole> findById(Integer userRoleId);

    //    ---------------Used in UserMapper----------------------------------------
    @Select("SELECT * FROM `UserRole` LEFT JOIN `Role` ON `UserRole`.`roleId` = `Role`.`roleId` WHERE `userId`=#{userId}")
    List<UserRole> findByUserId(Integer userId);
//    -------------------------------------------------------------------------

    @Select("SELECT * FROM `UserRole` LEFT JOIN `Role` ON `UserRole`.`roleId` = `Role`.`roleId` ORDER BY userRoleId DESC LIMIT 1")
    Optional<UserRole> findByMaxId();

    @Insert("INSERT INTO `UserRole` (`userId`, `roleId`) VALUES (#{userId}, #{roleId})")
    void add(UserRole userRole);

    @Update("UPDATE `UserRole` SET `userId`=#{userId}, `roleId`=#{roleId} WHERE `userRoleId`=#{userRoleId}")
    void update(UserRole userRole);

    @Delete("DELETE FROM `UserRole` WHERE `userRoleId` = #{userRoleId}")
    void deleteById(Integer userRoleId);
}
