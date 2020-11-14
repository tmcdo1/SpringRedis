package com.credera.SpringRedis.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ORDER")
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private int zip;
    @OneToMany
    @JoinColumn(name = "id")
    private CustomerEntity customer;
}
