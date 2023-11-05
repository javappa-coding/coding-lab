package com.javappa.start.security.infrastructure;

import com.javappa.start.security.domain.User;
import com.javappa.start.security.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DatabaseInitializer {

    private final UserRepository userRepository;

    @PostConstruct
    private void initializeDatabase() {
        Optional<User> existingUser = userRepository.findByUsername("doej");
        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername("doej");
            newUser.setPassword("aaaa"); //TODO: To be encoded
            userRepository.save(newUser);
        }
    }
}