package com.example.common_lib.entity.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {
    private String id;
    private String username;
}
