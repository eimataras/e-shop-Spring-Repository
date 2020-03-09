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
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/:max-id")
    public @ResponseBody
    Optional<OrderAndUserAndStatus> getOrderByMaxId() {
        return orderMapper.findByMaxId();
    }

    @PostMapping("/add")
    public @ResponseBody Optional<OrderAndUserAndStatus> addOrder(@RequestBody Order order) {
        orderMapper.add(order);

        return orderMapper.findByMaxId();
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
