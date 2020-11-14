package com.credera.SpringRedis.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
}
