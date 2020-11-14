package com.credera.SpringRedis.controllers;

import com.credera.SpringRedis.dtos.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @PostMapping("/")
    public ResponseEntity createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.noContent().build();
    }
}
