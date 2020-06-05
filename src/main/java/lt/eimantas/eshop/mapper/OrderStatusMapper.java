package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.OrderStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderStatusMapper {

    @Select("SELECT * FROM `OrderStatus`")
    List<OrderStatus> findAll();

    @Select("SELECT * FROM `OrderStatus` WHERE `statusId`=#{statusId}")
    Optional<OrderStatus> findById(Integer statusId);

    @Insert("INSERT INTO `OrderStatus` (`type`) VALUES (#{type})")
//    @SelectKey(statement = "SELECT SCOPE_IDENTITY()", keyProperty = "id", before = false, resultType = Integer.class)
    void add(OrderStatus status);

    @Update("UPDATE `OrderStatus` SET `type`=#{type} WHERE `statusId`=#{statusId}")
    void update(OrderStatus status);

    @Delete("DELETE FROM `OrderStatus` WHERE `statusId` = #{statusId}")
    void deleteById(Integer statusId);
}