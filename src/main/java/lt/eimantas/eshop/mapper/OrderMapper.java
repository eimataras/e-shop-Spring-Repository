package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.OrderAndUserAndStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`user_id` = `User`.`user_id` LEFT JOIN `OrderStatus` ON `Order`.`status_id` = `OrderStatus`.`status_id`")
    List<OrderAndUserAndStatus> findAll();
}
