package me.tmcd.SpringRedis.mappers;

import me.tmcd.SpringRedis.dtos.CustomerDto;
import me.tmcd.SpringRedis.entities.CustomerEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public CustomerEntity dtoToEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setName(customerDto.getName());
        if (customerDto.getId() != null) {
            customerEntity.setId(customerDto.getId());
        }
        return customerEntity;
    }

    public CustomerDto entityToDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerEntity.getId());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setName(customerEntity.getName());
        return customerDto;
    }
}
