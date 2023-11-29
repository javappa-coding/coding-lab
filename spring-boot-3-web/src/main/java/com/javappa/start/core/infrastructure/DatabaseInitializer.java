package com.javappa.start.core.infrastructure;

import com.javappa.start.core.security.domain.User;
import com.javappa.start.core.security.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DatabaseInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initializeDatabase() {
        Optional<User> existingUser = userRepository.findByUsername("doej");
        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername("doej");
            newUser.setPassword(passwordEncoder.encode("aaaa"));
            userRepository.save(newUser);
        }
    }
}