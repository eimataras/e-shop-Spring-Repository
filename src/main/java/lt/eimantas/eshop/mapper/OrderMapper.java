package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.Order;
import lt.eimantas.eshop.model.OrderAndUserAndStatus;
import lt.eimantas.eshop.model.OrderAndUserAndStatusAndItemsAndBook;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`user_id` = `User`.`user_id` LEFT JOIN `OrderStatus` ON `Order`.`status_id` = `OrderStatus`.`status_id` ORDER BY `order_id`")
    @Results({
            @Result(id = true, property = "order_id", column = "order_id"),
            @Result(property = "items", column = "order_id",
                    many = @Many(select = "lt.eimantas.eshop.mapper.OrderItemsMapper.findByOrderId"))
    })
    List<OrderAndUserAndStatus> findAll();

    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`user_id` = `User`.`user_id` LEFT JOIN `OrderStatus` ON `Order`.`status_id` = `OrderStatus`.`status_id` WHERE `order_id`=#{order_id}")
    @Results({
            @Result(id = true, property = "order_id", column = "order_id"),
            @Result(property = "items", column = "order_id",
                    many = @Many(select = "lt.eimantas.eshop.mapper.OrderItemsMapper.findByOrderId"))
    })
    Optional<OrderAndUserAndStatus> findById(Integer order_id);


    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`user_id` = `User`.`user_id` LEFT JOIN `OrderStatus` ON `Order`.`status_id` = `OrderStatus`.`status_id` ORDER BY order_id DESC LIMIT 1")
    @Results({
            @Result(id = true, property = "order_id", column = "order_id"),
            @Result(property = "items", column = "order_id",
                    many = @Many(select = "lt.eimantas.eshop.mapper.OrderItemsMapper.findByOrderId"))
    })
    Optional<OrderAndUserAndStatus> findByMaxId();


    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`user_id` = `User`.`user_id` LEFT JOIN `OrderStatus` ON `Order`.`status_id` = `OrderStatus`.`status_id` LEFT JOIN `OrderItems` ON `Order`.`order_id` = `OrderItems`.`order_id` LEFT JOIN `Book` ON `OrderItems`.`book_id` = `Book`.`book_id` WHERE `username`=#{username}")
    List<OrderAndUserAndStatusAndItemsAndBook> findByUsername(String username);

    @Insert("INSERT INTO `Order` (`user_id`, `status_id`) VALUES (#{user_id}, #{status_id})")
    void add(Order order);

    @Update("UPDATE `Order` SET `user_id`=#{user_id}, `status_id`=#{status_id} WHERE `order_id`=#{order_id}")
    void update(Order order);

    @Delete("DELETE FROM `Order` WHERE `order_id` = #{order_id}")
    void deleteById(Integer book_id);
}