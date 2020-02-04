package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.OrderStatusMapper;
import lt.eimantas.eshop.mapper.UserMapper;
import lt.eimantas.eshop.model.OrderStatus;
import lt.eimantas.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/status")
public class OrderStatusController {

    @Autowired
    private OrderStatusMapper orderStatusMapper;


    @GetMapping("/all")
    public List<OrderStatus> getAllStatuses() {
        return orderStatusMapper.findAll();
    }

    @GetMapping("/:id")
    public @ResponseBody
    Optional<OrderStatus> getOneStatusById(@RequestParam Integer status_id) {
        Optional<OrderStatus> result = orderStatusMapper.findById(status_id);
        return result;
    }

    @PostMapping("/add")
    public void addOrderStatus(@RequestBody OrderStatus orderStatus) {
        orderStatusMapper.add(orderStatus);
    }

    @PutMapping("/edit")
    public void updateStatus(@RequestBody OrderStatus orderStatus) {
        orderStatusMapper.update(orderStatus);
    }

    @DeleteMapping("/delete")
    public void deleteOrderStatus(@RequestParam Integer status_id) {
        orderStatusMapper.deleteById(status_id);
    }
}
