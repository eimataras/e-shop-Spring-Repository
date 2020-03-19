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
    public @ResponseBody
    Optional<OrderItems> getOneOrderItemById(@RequestParam Integer order_item_id) {
        return orderItemsMapper.findById(order_item_id);
    }

    //suranda visus OrderItems su Book informacija pagal order_id
    @GetMapping("/:orderId")
    public @ResponseBody
    List<OrderItemsAndBook> getAllOrderItemsByOrderId(@RequestParam Integer order_id) {
        return orderItemsMapper.findByOrderId(order_id);
    }

    @PostMapping("/add")
    public Optional<OrderAndUserAndStatus> addOrderItem(@RequestBody OrderItems orderItems) {

//      Jei si knyga dar nera itraukta i orderi, sukuriam nauja OrderItems irasa siam orderiui
        Integer order_item_id = orderItemsMapper.findOptionalId(orderItems.getOrder_id(), orderItems.getBook_id());
        if (order_item_id == null) {
            orderItemsMapper.add(orderItems);

//      Jei knyga jau itraukta i orderi, editinam OrderItems irasa pakeisdami tik quantity
        } else {
            Integer quantity = orderItemsMapper.findOptionalQuantity(order_item_id);
            OrderItems orderItem = new OrderItems(order_item_id, orderItems.getOrder_id(), orderItems.getBook_id(), quantity + 1);
            orderItemsMapper.update(orderItem);
        }

        return orderMapper.findById(orderItems.getOrder_id());
    }

    @PutMapping("/edit")
    public Optional<OrderAndUserAndStatus> updateOrderItem(@RequestBody OrderItems orderItems) {

//      Jei is fronto ateina quantity = 0, tuomet istrinam OrderItems irasa
        if (orderItems.getQuantity() == 0) {
            orderItemsMapper.deleteById(orderItems.getOrder_item_id());

//          Jei siam orderiui tai buvo paskutinis OrderItems irasas, tuomet istrinam ir pati orderi
//            if (orderItemsMapper.findSingleIdByOrderId(orderItems.getOrder_id()) == null) {
//                orderMapper.deleteById(orderItems.getOrder_id());
//            }
        } else {
            orderItemsMapper.update(orderItems);
        }

        return orderMapper.findById(orderItems.getOrder_id());
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam Integer order_item_id) {
        orderItemsMapper.deleteById(order_item_id);
    }
}