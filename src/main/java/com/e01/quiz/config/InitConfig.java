package com.e01.quiz.config;

import com.e01.quiz.entity.User;
import com.e01.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            User admin = userRepository.findByUsername("admin@gmail.com").orElse(null);
            if (admin == null) {
                admin = new User();
                admin.setName("admin");
                admin.setUsername("admin@gmail.com");
//                admin.setPassword(passwordEncoder.encode("123456"));
//                admin.setRoles(List.of(UserRole.ROLE_ADMIN));
                userRepository.save(admin);
            }
        };
    }
}
