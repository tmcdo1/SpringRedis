package me.tmcd.SpringRedis.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private int zip;
    @ManyToOne(optional=false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;
}
