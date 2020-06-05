package lt.eimantas.eshop.controller;

import lt.eimantas.eshop.mapper.OrderItemsMapper;
import lt.eimantas.eshop.mapper.OrderMapper;
import lt.eimantas.eshop.model.Order;
import lt.eimantas.eshop.model.OrderAndUserAndStatus;
import lt.eimantas.eshop.model.OrderAndUserAndStatusAndItemsAndBook;
import lt.eimantas.eshop.model.OrderItems;
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

    @Autowired
    OrderItemsMapper orderItemsMapper;


    @GetMapping("/all")
    public List<OrderAndUserAndStatus> getAllOrders() {
        return orderMapper.findAll();
    }

    @GetMapping("/:id")
    public Optional<OrderAndUserAndStatus> getOneOrderById(@RequestParam Integer orderId) {
        return orderMapper.findById(orderId);
    }

    @GetMapping("/:username")
    public List<OrderAndUserAndStatusAndItemsAndBook> getFullOrderInfoByUsername(@RequestParam String username) {
        return orderMapper.findByUsername(username);
    }

    @GetMapping("/:max-id")
    public Optional<OrderAndUserAndStatus> getOrderByMaxId() {
        return orderMapper.findByMaxId();
    }


    @PostMapping("/add")
    public Optional<OrderAndUserAndStatus> addOrder(@RequestBody Order order) {

//      Patikrinam ar besikreipianciam vartotojui egzistuoja orderis su statusu NEW
        Integer orderAlreadyExistId = orderMapper.findOptionalId(order.getUserId(), order.getStatusId());
        if (orderAlreadyExistId == null) {

//          Jei neegzistuoja, tuomet sukuriam jam nauja orderi su statusu NEW
            orderMapper.add(order);
            order.setOrderId(orderMapper.findMaxId());

//          Ir automatiskai sukuriam irasa OrderItems lenteleje katik sukurtam naujam orderiui
            OrderItems orderItems = new OrderItems(order.getOrderId(), order.getBookId(), 1);
            orderItemsMapper.add(orderItems);
        }
        return orderMapper.findByMaxId();
    }

    @PutMapping("/edit")
    public Optional<OrderAndUserAndStatus> updateOrder(@RequestBody Order order) {
        orderMapper.update(order);
        Integer id = order.getOrderId();
        return orderMapper.findById(id);
    }

    @DeleteMapping("/delete")
    public Optional<OrderAndUserAndStatus> deleteOrder(@RequestParam Integer orderId) {
        Optional<OrderAndUserAndStatus> result = orderMapper.findById(orderId);
        orderMapper.deleteById(orderId);
        return result;
    }
}
