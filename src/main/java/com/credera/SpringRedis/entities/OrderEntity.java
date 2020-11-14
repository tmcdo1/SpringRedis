package com.credera.SpringRedis.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private int zip;
    @OneToMany
    @JoinColumn(name = "id")
    private CustomerEntity custId;
}
