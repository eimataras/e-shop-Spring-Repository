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

    @Select("SELECT * FROM `OrderItems` WHERE `orderItemId`=#{orderItemId}")
    Optional<OrderItems> findById(Integer orderItemId);

    //---------used in Order Mapper-----------------------------------------------------------------------------------
    @Select("SELECT * FROM `OrderItems` LEFT JOIN `Book` ON `OrderItems`.`bookId` = `Book`.`bookId` WHERE `orderId`=#{orderId}")
    List<OrderItemsAndBook> findByOrderId(Integer orderId);

    @Select("SELECT orderItemId FROM `OrderItems` WHERE `orderId`=#{orderId} LIMIT 1")
    Integer findSingleIdByOrderId(Integer orderId);

    @Select("SELECT orderItemId FROM `OrderItems` WHERE `orderId`=#{orderId} AND `bookId`=#{bookId}")
    Integer findOptionalId(Integer orderId, Integer bookId);

    @Select("SELECT quantity FROM `OrderItems` WHERE `orderItemId`=#{orderItemId}")
    Integer findOptionalQuantity (Integer orderItemId);

    @Insert("INSERT INTO `OrderItems` (`orderId`, `bookId`, `quantity`) VALUES (#{orderId}, #{bookId}, #{quantity})")
    void add(OrderItems orderItems);

    @Update("UPDATE `OrderItems` SET `orderId`=#{orderId}, `bookId`=#{bookId}, `quantity`=#{quantity} WHERE `orderItemId`=#{orderItemId}")
    void update(OrderItems orderItems);

    @Delete("DELETE FROM `OrderItems` WHERE `orderItemId` = #{orderItemId}")
    void deleteById(Integer orderItemId);
}