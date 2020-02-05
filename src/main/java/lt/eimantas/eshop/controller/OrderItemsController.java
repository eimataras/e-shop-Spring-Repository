package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.OrderItemsMapper;
import lt.eimantas.eshop.model.OrderItems;
import lt.eimantas.eshop.model.OrderItemsAndBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsMapper orderItemsMapper;


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
    public void addOrderItem(@RequestBody OrderItems orderItems) {
        orderItemsMapper.add(orderItems);
    }

    @PutMapping("/edit")
    public void updateOrderItem(@RequestBody OrderItems orderItems) {
        orderItemsMapper.update(orderItems);
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam Integer order_item_id) {
        orderItemsMapper.deleteById(order_item_id);
    }
}