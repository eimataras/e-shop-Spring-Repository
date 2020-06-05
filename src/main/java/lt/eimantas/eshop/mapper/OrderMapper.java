package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.Order;
import lt.eimantas.eshop.model.OrderAndUserAndStatus;
import lt.eimantas.eshop.model.OrderAndUserAndStatusAndItemsAndBook;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`userId` = `User`.`userId` LEFT JOIN `OrderStatus` ON `Order`.`statusId` = `OrderStatus`.`statusId` ORDER BY `orderId`")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "items", column = "orderId",
                    many = @Many(select = "lt.eimantas.eshop.mapper.OrderItemsMapper.findByOrderId"))
    })
    List<OrderAndUserAndStatus> findAll();

    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`userId` = `User`.`userId` LEFT JOIN `OrderStatus` ON `Order`.`statusId` = `OrderStatus`.`statusId` WHERE `orderId`=#{orderId}")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "items", column = "orderId",
                    many = @Many(select = "lt.eimantas.eshop.mapper.OrderItemsMapper.findByOrderId"))
    })
    Optional<OrderAndUserAndStatus> findById(Integer orderId);


    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`userId` = `User`.`userId` LEFT JOIN `OrderStatus` ON `Order`.`statusId` = `OrderStatus`.`statusId` ORDER BY orderId DESC LIMIT 1")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "items", column = "orderId",
                    many = @Many(select = "lt.eimantas.eshop.mapper.OrderItemsMapper.findByOrderId"))
    })
    Optional<OrderAndUserAndStatus> findByMaxId();

    @Select("SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1 ")
    Integer findMaxId();

    @Select("SELECT orderId FROM `Order` WHERE `userId`=#{userId} AND `statusId`=#{statusId}")
    Integer findOptionalId(Integer userId, Integer statusId);


    @Select("SELECT * FROM `Order` LEFT JOIN `User` ON `Order`.`userId` = `User`.`userId` LEFT JOIN `OrderStatus` ON `Order`.`statusId` = `OrderStatus`.`statusId` LEFT JOIN `OrderItems` ON `Order`.`orderId` = `OrderItems`.`orderId` LEFT JOIN `Book` ON `OrderItems`.`bookId` = `Book`.`bookId` WHERE `username`=#{username}")
    List<OrderAndUserAndStatusAndItemsAndBook> findByUsername(String username);

    @Insert("INSERT INTO `Order` (`userId`, `statusId`) VALUES (#{userId}, #{statusId})")
    void add(Order order);

    @Update("UPDATE `Order` SET `userId`=#{userId}, `statusId`=#{statusId} WHERE `orderId`=#{orderId}")
    void update(Order order);

    @Delete("DELETE FROM `Order` WHERE `orderId` = #{orderId}")
    void deleteById(Integer bookId);
}