package com.example.common_lib.repository.cache;

import com.example.common_lib.entity.cache.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {
}
