package me.tmcd.SpringRedis.services;

import me.tmcd.SpringRedis.dtos.OrderDto;
import me.tmcd.SpringRedis.entities.OrderEntity;
import me.tmcd.SpringRedis.mappers.OrderMapper;
import me.tmcd.SpringRedis.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    // Used to insert or update a order
    @CachePut(value = "ORDER", key = "#result.getId()")
    public OrderDto upsertOrder(OrderDto orderDto) {
        OrderEntity ord = orderRepository.save(orderMapper.dtoToEntity(orderDto));
        return orderMapper.entityToDto(ord);
    }

    @Cacheable(value = "ORDER", key = "#id")
    public Optional<OrderDto> getOrder(Long id) {
        Optional<OrderEntity> ordEnt = orderRepository.findById(id);
        if (ordEnt.isPresent()) {
            OrderDto orderDto = orderMapper.entityToDto(ordEnt.get());
            return Optional.of(orderDto);
        } else {
            return Optional.empty();
        }
    }

    public List<OrderDto> getOrders() {
        List<OrderEntity> ordEnts = orderRepository.findAll();
        List<OrderDto> ordDtos = new ArrayList<>();
        for (OrderEntity ordEnt : ordEnts) {
            ordDtos.add(orderMapper.entityToDto(ordEnt));
        }
        return ordDtos;
    }

    @CacheEvict(value = "ORDER", key = "#id")
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
