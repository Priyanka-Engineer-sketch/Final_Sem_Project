package com.ecomm.user;

import com.ecomm.entity.User;
import com.ecomm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepo;
    private final org.springframework.security.crypto.password.PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (userRepo.count() == 0) {
            User admin = User.builder()
                    .email("admin@example.com")
                    .username("admin")
                    .passwordHash(encoder.encode("Admin@12345"))
                    .isActive(true)
                    .isEmailVerified(true)   // <<< ensure non-null and allow immediate login
                    .tokenVersion(1)
                    .build();
            userRepo.save(admin);

            User user = User.builder()
                    .email("user@example.com")
                    .username("user")
                    .passwordHash(encoder.encode("User@12345"))
                    .isActive(true)
                    .isEmailVerified(false)  // or true, as you prefer for seed
                    .tokenVersion(1)
                    .build();
            userRepo.save(user);
        }
    }
}
