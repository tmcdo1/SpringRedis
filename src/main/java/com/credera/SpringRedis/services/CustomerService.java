package com.credera.SpringRedis.services;

import com.credera.SpringRedis.configurations.CacheConfigurationProperties;
import com.credera.SpringRedis.dtos.CustomerDto;
import com.credera.SpringRedis.entities.CustomerEntity;
import com.credera.SpringRedis.mappers.CustomerMapper;
import com.credera.SpringRedis.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    // Used to insert or update a customer
    @CachePut(value = "CUSTOMER", key = "#result.getId()")
    public CustomerDto upsertCustomer(CustomerDto customerDto) {
        CustomerEntity cust = customerRepository.save(customerMapper.dtoToEntity(customerDto));
        return customerMapper.entityToDto(cust);
    }

    @Cacheable(value = "CUSTOMER", key = "#id")
    public Optional<CustomerDto> getCustomer(Long id) {
        Optional<CustomerEntity> custEnt = customerRepository.findById(id);
        if (custEnt.isPresent()) {
            CustomerDto customerDto = customerMapper.entityToDto(custEnt.get());
            return Optional.of(customerDto);
        } else {
            return Optional.empty();
        }
    }
    
    public List<CustomerDto> getCustomers() {
        List<CustomerEntity> custEnts = customerRepository.findAll();
        List<CustomerDto> custDtos = new ArrayList<>();
        for (CustomerEntity custEnt : custEnts) {
            custDtos.add(customerMapper.entityToDto(custEnt));
        }
        return custDtos;
    }

    @CacheEvict(value = "CUSTOMER", key = "#id")
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
