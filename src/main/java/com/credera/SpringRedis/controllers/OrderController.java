package com.credera.SpringRedis.controllers;

import com.credera.SpringRedis.dtos.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/")
    public ResponseEntity createOrder(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@RequestBody CustomerDto customerDto, @PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrder(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
