package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.OrderItemsMapper;
import lt.eimantas.eshop.mapper.OrderMapper;
import lt.eimantas.eshop.model.OrderAndUserAndStatus;
import lt.eimantas.eshop.model.OrderItems;
import lt.eimantas.eshop.model.OrderItemsAndBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderMapper orderMapper;


    @GetMapping("/all")
    public List<OrderItems> getAllOrderItems() {
        return orderItemsMapper.findAll();
    }

    @GetMapping("/:id")
    public Optional<OrderItems> getOneOrderItemById(@RequestParam Integer orderItemId) {
        return orderItemsMapper.findById(orderItemId);
    }

    //suranda visus OrderItems su Book informacija pagal order_id
    @GetMapping("/:orderId")
    public List<OrderItemsAndBook> getAllOrderItemsByOrderId(@RequestParam Integer orderId) {
        return orderItemsMapper.findByOrderId(orderId);
    }

    @PostMapping("/add")
    public Optional<OrderAndUserAndStatus> addOrderItem(@RequestBody OrderItems orderItems) {

//      Jei si knyga dar nera itraukta i orderi, sukuriam nauja OrderItems irasa siam orderiui
        Integer orderItemId = orderItemsMapper.findOptionalId(orderItems.getOrderId(), orderItems.getBookId());
        if (orderItemId == null) {
            orderItemsMapper.add(orderItems);

//      Jei knyga jau itraukta i orderi, editinam OrderItems irasa pakeisdami tik quantity
        } else {
            Integer quantity = orderItemsMapper.findOptionalQuantity(orderItemId);
            OrderItems orderItem = new OrderItems(orderItemId, orderItems.getOrderId(), orderItems.getBookId(), quantity + 1);
            orderItemsMapper.update(orderItem);
        }
        return orderMapper.findById(orderItems.getOrderId());
    }

    @PutMapping("/edit")
    public Optional<OrderAndUserAndStatus> updateOrderItem(@RequestBody OrderItems orderItems) {

//      Jei is fronto ateina quantity = 0, tuomet istrinam OrderItems irasa
        if (orderItems.getQuantity() == 0) {
            orderItemsMapper.deleteById(orderItems.getOrderItemId());

//          Jei siam orderiui tai buvo paskutinis OrderItems irasas, tuomet istrinam ir pati orderi
//            if (orderItemsMapper.findSingleIdByOrderId(orderItems.getOrderId()) == null) {
//                orderMapper.deleteById(orderItems.getOrderId());
//            }
        } else {
            orderItemsMapper.update(orderItems);
        }
        return orderMapper.findById(orderItems.getOrderId());
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam Integer orderItemId) {
        orderItemsMapper.deleteById(orderItemId);
    }
}