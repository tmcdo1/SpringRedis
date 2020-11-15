package com.credera.SpringRedis.controllers;

import com.credera.SpringRedis.dtos.OrderDto;
import com.credera.SpringRedis.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        try {
            OrderDto newOrd = orderService.upsertOrder(orderDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrd);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@RequestBody OrderDto orderDto, @PathVariable Long id) {
        try {
            orderDto.setId(id);
            OrderDto newOrd = orderService.upsertOrder(orderDto);
            return ResponseEntity.ok(newOrd);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating order");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrder(@PathVariable Long id) {
        try {
            Optional<OrderDto> ord = orderService.getOrder(id);
            if(ord.isPresent()) {
                return ResponseEntity.ok(ord.get());
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Error retrieving order: %d",id));
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllOrders() {
        try {
            List<OrderDto> ords = orderService.getOrders();
            return ResponseEntity.ok(ords);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving orders");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Error deleting order: %d",id));
        }
    }
}
