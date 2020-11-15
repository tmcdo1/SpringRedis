package me.tmcd.SpringRedis.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private Long id;
    private String name;
    private String email;
}
