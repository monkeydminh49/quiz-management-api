package com.e01.quiz.config;

import com.e01.quiz.entity.User;
import com.e01.quiz.repository.QuestionRepository;
import com.e01.quiz.repository.UserRepository;
import com.e01.quiz.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class InitConfig {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            User admin = userRepository.findByUsername("admin@gmail.com").orElse(null);
            if (admin == null) {
                admin = new User();
                admin.setName("admin");
                admin.setUsername("admin@gmail.com");
                admin.setRoles(List.of(UserRole.STUDENT, UserRole.TEACHER));
                admin.setPassword(passwordEncoder.encode("123456"));
                userRepository.save(admin);
            }
//            Question ques = questionRepository.findById((1L)).orElse(null);
//            if (ques == null){
//                ques= new Question();
//                questionRepository.save(ques);
//            }
        };
    }
}
