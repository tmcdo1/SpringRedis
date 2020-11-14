package com.credera.SpringRedis.dtos;

import com.credera.SpringRedis.entities.CustomerEntity;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Data
public class OrderDto {
    private Long id;
    private String street;
    private int zip;
    private Long custId;
}
