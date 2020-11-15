package me.tmcd.SpringRedis.mappers;

import me.tmcd.SpringRedis.dtos.CustomerDto;
import me.tmcd.SpringRedis.dtos.OrderDto;
import me.tmcd.SpringRedis.entities.CustomerEntity;
import me.tmcd.SpringRedis.entities.OrderEntity;
import me.tmcd.SpringRedis.repositories.CustomerRepository;
import me.tmcd.SpringRedis.repositories.redis.RedisCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderMapper {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RedisCustomerRepository redisCustomerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public OrderEntity dtoToEntity(OrderDto orderDto) {
        OrderEntity ordEnt = new OrderEntity();
        ordEnt.setStreet(orderDto.getStreet());
        ordEnt.setZip(orderDto.getZip());
        // Try getting the customer from cache. Get from db if not in cache
        Optional<CustomerDto> customerDto = redisCustomerRepository.findById(orderDto.getCustId());
        if (customerDto.isPresent()) {
            ordEnt.setCustomer(customerMapper.dtoToEntity(customerDto.get()));
        } else {
            // Get the customer associated with custId from db
            Optional<CustomerEntity> customer = customerRepository.findById(orderDto.getCustId());
            if (customer.isPresent()) {
                ordEnt.setCustomer(customer.get());
            }
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
