package com.credera.SpringRedis.dtos;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrderDto implements Serializable {
    private Long id;
    private String street;
    private int zip;
    private Long custId;
}
