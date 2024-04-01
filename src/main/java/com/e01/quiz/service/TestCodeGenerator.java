package com.e01.quiz.service;

import com.e01.quiz.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TestCodeGenerator {

    private static final int CODE_LENGTH = 7;
    private static final String CODE_CHARS = "0123456789";
    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private TestRepository testRepository;

    public String generateUniqueTestCode() {
        String code;
        do {
            code = generateTestCode();
        } while (testRepository.findByCode(code).isPresent());

        return code;
    }

    private String generateTestCode() {
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CODE_CHARS.length());
            char randomChar = CODE_CHARS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
}
