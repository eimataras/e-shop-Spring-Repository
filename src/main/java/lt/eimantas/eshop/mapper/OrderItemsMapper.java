package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.OrderItems;
import lt.eimantas.eshop.model.OrderItemsAndBook;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderItemsMapper {

    @Select("SELECT * FROM `OrderItems`")
    List<OrderItems> findAll();

    @Select("SELECT * FROM `OrderItems` WHERE `order_item_id`=#{order_item_id}")
    Optional<OrderItems> findById(Integer order_item_id);

    //---------used in Order Mapper-----------------------------------------------------------------------------------
    @Select("SELECT * FROM `OrderItems` LEFT JOIN `Book` ON `OrderItems`.`book_id` = `Book`.`book_id` WHERE `order_id`=#{order_id}")
    List<OrderItemsAndBook> findByOrderId(Integer order_id);

    @Select("SELECT order_item_id FROM `OrderItems` WHERE `order_id`=#{order_id} AND `book_id`=#{book_id} AND `quantity`=#{optionalOrderItemQuantity}")
    Integer findOptionalId(Integer order_id, Integer book_id, Integer optionalOrderItemQuantity);

    @Select("SELECT quantity FROM `OrderItems` WHERE `order_id`=#{order_id} AND `book_id`=#{book_id}")
    Integer findOptionalQuantity (Integer order_id, Integer book_id);

    @Insert("INSERT INTO `OrderItems` (`order_id`, `book_id`, `quantity`) VALUES (#{order_id}, #{book_id}, #{quantity})")
    void add(OrderItems orderItems);

    @Update("UPDATE `OrderItems` SET `order_id`=#{order_id}, `book_id`=#{book_id}, `quantity`=#{quantity} WHERE `order_item_id`=#{order_item_id}")
    void update(OrderItems orderItems);

    @Delete("DELETE FROM `OrderItems` WHERE `order_item_id` = #{order_item_id}")
    void deleteById(Integer order_item_id);
}