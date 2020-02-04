package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.OrderStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderStatusMapper {

    @Select("SELECT * FROM `OrderStatus`")
    List<OrderStatus> findAll();

    @Select("SELECT * FROM `OrderStatus` WHERE `status_id`=#{status_id}")
    Optional<OrderStatus> findById(Integer status_id);

    @Insert("INSERT INTO `OrderStatus` (`type`) VALUES (#{type})")
    @SelectKey(statement = "SELECT SCOPE_IDENTITY()", keyProperty = "id", before = false, resultType = Integer.class)
    void add(OrderStatus status);

    @Update("UPDATE `OrderStatus` SET `type`=#{type} WHERE `status_id`=#{status_id}")
    void update(OrderStatus status);

    @Delete("DELETE FROM `OrderStatus` WHERE `status_id` = #{status_id}")
    void deleteById(Integer status_id);
}
