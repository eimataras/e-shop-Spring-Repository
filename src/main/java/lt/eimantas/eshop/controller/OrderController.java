package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.OrderMapper;
import lt.eimantas.eshop.model.OrderAndUserAndStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/all")
    public List<OrderAndUserAndStatus> getAllOrders() {
        return orderMapper.findAll();
    }
}
