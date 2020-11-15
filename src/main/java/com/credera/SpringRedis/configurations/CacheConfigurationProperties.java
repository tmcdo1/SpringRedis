package com.credera.SpringRedis.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.redis")
@Data
public class CacheConfigurationProperties {

    private String redisHost;

    private String redisPass;

    private int redisPort;

    private long timeoutSeconds = 60;

    private String customerCacheName = "CUSTOMER";
    private String orderCacheName = "ORDER";

    // Mapping of cacheNames to expiration-after-write timeout in seconds
    private Map<String, Long> cacheExpirations = new HashMap<>();
}

