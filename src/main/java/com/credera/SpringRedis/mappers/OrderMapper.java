package com.credera.SpringRedis.mappers;

import com.credera.SpringRedis.dtos.OrderDto;
import com.credera.SpringRedis.entities.CustomerEntity;
import com.credera.SpringRedis.entities.OrderEntity;
import com.credera.SpringRedis.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderMapper {
    @Autowired
    private CustomerRepository customerRepository;

    public OrderEntity dtoToEntity(OrderDto orderDto) {
        OrderEntity ordEnt = new OrderEntity();
        ordEnt.setStreet(orderDto.getStreet());
        ordEnt.setZip(orderDto.getZip());
        // Get the customer associated with custId
        Optional<CustomerEntity> customer = customerRepository.findById(orderDto.getCustId());
        if (customer.isPresent()) {
            ordEnt.setCustomer(customer.get());
        }
        // Set id if it exists
        if (orderDto.getId() != null) {
            ordEnt.setId(ordEnt.getId());
        }
        return ordEnt;
    }

    public OrderDto entityToDto(OrderEntity orderEntity) {
        OrderDto ordDto = new OrderDto();
        ordDto.setId(orderEntity.getId());
        ordDto.setCustId(orderEntity.getCustomer().getId());
        ordDto.setStreet(orderEntity.getStreet());
        ordDto.setZip(orderEntity.getZip());
        return ordDto;
    }
}
