package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.OrderMapper;
import lt.eimantas.eshop.model.Order;
import lt.eimantas.eshop.model.OrderAndUserAndStatus;
import lt.eimantas.eshop.model.OrderAndUserAndStatusAndItemsAndBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/all")
    public List<OrderAndUserAndStatus> getAllOrders() {
        return orderMapper.findAll();
    }

    @GetMapping("/:id")
    public @ResponseBody
    Optional<OrderAndUserAndStatus> getOneOrderById(@RequestParam Integer order_id) {
        return orderMapper.findById(order_id);
    }

    @GetMapping("/:username")
    public @ResponseBody
    List<OrderAndUserAndStatusAndItemsAndBook> getFullOrderInfoByUsername(@RequestParam String username) {
        return orderMapper.findByUsername(username);
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody Order order) {
        orderMapper.add(order);
    }

    @PutMapping("/edit")
    public void updateOrder(@RequestBody Order order) {
        orderMapper.update(order);
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@RequestParam Integer order_id) {
        orderMapper.deleteById(order_id);
    }
}
