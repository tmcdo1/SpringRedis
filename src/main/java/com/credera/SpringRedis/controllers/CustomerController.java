package com.credera.SpringRedis.controllers;

import com.credera.SpringRedis.dtos.CustomerDto;
import com.credera.SpringRedis.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            CustomerDto newCust = customerService.upsertCustomer(customerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCust);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating customer");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Long id) {
        try {
            customerDto.setId(id);
            CustomerDto newCust = customerService.upsertCustomer(customerDto);
            return ResponseEntity.ok(newCust);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating customer");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable Long id) {
        try {
            Optional<CustomerDto> cust = customerService.getCustomer(id);
            if(cust.isPresent()) {
                return ResponseEntity.ok(cust.get());
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Error retrieving customer: %d",id));
        }

    }

    @GetMapping("/")
    public ResponseEntity getAllCustomers() {
        try {
            List<CustomerDto> custs = customerService.getCustomers();
            return ResponseEntity.ok(custs);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving customers");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Error deleting customer: %d",id));
        }
    }
}
