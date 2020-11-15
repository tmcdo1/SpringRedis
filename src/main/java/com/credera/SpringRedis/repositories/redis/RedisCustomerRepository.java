package com.credera.SpringRedis.repositories.redis;

import com.credera.SpringRedis.configurations.CacheConfigurationProperties;
import com.credera.SpringRedis.dtos.CustomerDto;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class RedisCustomerRepository {
    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    private CacheConfigurationProperties properties;

    public RedisCustomerRepository(RedisTemplate redisTemplate, CacheConfigurationProperties properties){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        this.properties = properties;
    }

    public void save(CustomerDto customer){
        hashOperations.put(properties.getCustomerCacheName(), customer.getId(), customer);
    }

    public List findAll(){
        return hashOperations.values(properties.getCustomerCacheName());
    }

    public Optional<CustomerDto> findById(Long id){
        CustomerDto customer = (CustomerDto) hashOperations.get(properties.getCustomerCacheName(), id);
        return Optional.ofNullable(customer);
    }

    public void delete(Long id){
        hashOperations.delete(properties.getCustomerCacheName(), id);
    }
}
