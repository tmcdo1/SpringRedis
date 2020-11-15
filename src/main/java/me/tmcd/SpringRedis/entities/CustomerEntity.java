package me.tmcd.SpringRedis.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CUSTOMER")
public class CustomerEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
}
