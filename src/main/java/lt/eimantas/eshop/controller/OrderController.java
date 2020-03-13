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
    public @ResponseBody
    Optional<OrderAndUserAndStatus> addOrder(@RequestBody Order order) {

//      Patikrinam ar besikreipianciam vartotojui egzistuoja orderis su statusu NEW
        Integer orderAlreadyExistId = orderMapper.findOptionalId(order.getUser_id(), order.getStatus_id());
        if (orderAlreadyExistId == null) {

//          Jei neegzistuoja, tuomet sukuriam jam nauja orderi su statusu NEW
            orderMapper.add(order);
            order.setOrder_id(orderMapper.findMaxId());

//          Ir auomatiskai sukuriam irasa OrderItems lenteleje katik sukurtam naujam orderiui
            OrderItems orderItems = new OrderItems(order.getOrder_id(), order.getBook_id(), 1);
            orderItemsMapper.add(orderItems);
        } else {

//          Jei egzistuoja, tuomet paprasom knygu, jau pridetu i egzistuojanti orderi, kiekio ir
//          patikrinam ar si knyga apsikritai jau buvo prideta
            Integer optionalOrderItemQuantity = orderItemsMapper.findOptionalQuantity(orderAlreadyExistId, order.getBook_id());
            if (optionalOrderItemQuantity == null) {

//            Jei dar nebuvo, tuomet kuriam nauja OrderItems irasa ivesdami 1 knyga
                OrderItems orderItems = new OrderItems(orderAlreadyExistId, order.getBook_id(), 1);
                orderItemsMapper.add(orderItems);
            } else {

//            Jei jau buvo, tuomet editinam OrderItems irasa pakeisdami tik quantity + 1
                Integer orderItemAlreadyExistId = orderItemsMapper.findOptionalId(orderAlreadyExistId, order.getBook_id(), optionalOrderItemQuantity);
                Integer quantity = optionalOrderItemQuantity + 1;
                OrderItems orderItems = new OrderItems(orderItemAlreadyExistId, orderAlreadyExistId, order.getBook_id(), quantity);
                orderItemsMapper.update(orderItems);
            }
        }
        return orderMapper.findByMaxId();
    }

    @PutMapping("/edit")
    public @ResponseBody
    Optional<OrderAndUserAndStatus> updateOrder(@RequestBody Order order) {
        orderMapper.update(order);
        Integer id = order.getOrder_id();

        return orderMapper.findById(id);
    }

    @DeleteMapping("/delete")
    public @ResponseBody
    Optional<OrderAndUserAndStatus> deleteOrder(@RequestParam Integer order_id) {
        Optional<OrderAndUserAndStatus> result = orderMapper.findById(order_id);
        orderMapper.deleteById(order_id);

        return result;
    }
}
