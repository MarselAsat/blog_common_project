package org.example.blog.service;

import com.example.common_lib.entity.cache.User;
import com.example.common_lib.repository.cache.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${user-service.endpoint}")
    private String usersEndpoint;

    private final RestOperations restOperations;
    private final UserRepository userRepository;
    @Override
    public String getUsername(String id) {
        return userRepository.findById(id).orElseGet(
                () -> restOperations.getForObject(usersEndpoint + "/" + id, User.class)).getUsername();
    }
}
