package com.e01.quiz.service;

import com.e01.quiz.dto.ChoiceDTO;
import com.e01.quiz.dto.QuestionDTO;
import com.e01.quiz.dto.TestDTO;
import com.e01.quiz.entity.Choice;
import com.e01.quiz.entity.Question;
import com.e01.quiz.entity.Test;
import com.e01.quiz.entity.User;
import com.e01.quiz.repository.ChoiceRepository;
import com.e01.quiz.repository.QuestionRepository;
import com.e01.quiz.repository.TestRepository;
import com.e01.quiz.component.Mapper;
import com.e01.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestCodeGenerator testCodeGenerator;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private Mapper mapper;

    public List<Test> getTests(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
        return testRepository.getTestsForUser(user);
    }

    public Test createTest(String username, Test test) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
        String uniqueCode = testCodeGenerator.generateUniqueTestCode();
        test.setCode(uniqueCode);
        test.setUser(user);
//        List<Question> questions = test.getQuestions();
//        test.getQuestions().forEach(question -> {
//            question.setTest(test);
//            List<Choice> choices = question.getChoices();
//            choices.forEach(choice -> {
//                choice.setQuestion(question);
//            });
////                System.out.println(question.getChoices().getClass());
////                question.getChoices().removeAll(question.getChoices());
////            question.setChoices(new ArrayList<>());
////            question.getChoices().addAll(choices);
////                choiceRepository.saveAll(choices);
//        });
////        test.setQuestions(questions);
//
        testRepository.save(test);
        questionService.saveQuestions(test.getQuestions(), test);

        return test;
    }

    public Optional<Test> getUserTestById(String username, Long id) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));

        if (!user.hasTest(id)) {
            throw new RuntimeException("User " + username + " does not have test with id " + id);
        }
        Optional<Test> optionalTest = testRepository.findById(id);
        if (optionalTest.isPresent()){
            return optionalTest;
        } else {
            throw new RuntimeException("test not found");
        }

    }

//    public Test updateTest(String username, Long id, TestDTO testDTO) {
//        // Find the user by username
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Find the test by id
//        Test test = testRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Test not found"));
//
//        // Check if the test belongs to the user
//        if (!test.getUser().getId().equals(user.getId())) {
//            throw new RuntimeException("Test does not belong to the user");
//        }
//
//        // Update test properties
//        test.setCode(testDTO.getCode());
//        test.setTitle(testDTO.getTitle());
//        test.setStartTime(testDTO.getStartTime());
//        test.setDuration(testDTO.getDuration());
//
//        // Update or create questions and choices
//        List<Question> updatedQuestions = testDTO.getQuestions().stream()
//                .map(questionDTO -> {
//                    Question question;
//                    if (questionDTO.getId() != null) {
//                        question = questionRepository.findById(questionDTO.getId())
//                                .orElseThrow(() -> new RuntimeException("Question not found"));
//                    } else {
//                        question = new Question();
//                    }
//                    question.setQuestion(questionDTO.getQuestion());
//                    question.setTest(test);
//
//                    // Update or create choices
//                    List<Choice> updatedChoices = questionDTO.getChoices().stream()
//                            .map(choiceDTO -> {
//                                Choice choice;
//                                if (choiceDTO.getId() != null) {
//                                    choice = choiceRepository.findById(choiceDTO.getId())
//                                            .orElseThrow(() -> new RuntimeException("Choice not found"));
//                                } else {
//                                    choice = new Choice();
//                                }
//                                choice.setContent(choiceDTO.getContent());
//                                choice.setCorrect(choiceDTO.getIsCorrect());
//                                choice.setQuestion(question);
//                                return choice;
//                            })
//                            .collect(Collectors.toList());
//
//                    question.setChoices(updatedChoices);
//
//                    return question;
//                })
//                .collect(Collectors.toList());
//
//        test.setQuestions(updatedQuestions);
//
//
//
//        return testRepository.save(test);
//    }

    public Test updateTest(String username, Long id, TestDTO testDTO) {
        Optional<Test> optionalTest = testRepository.findById(id);
        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();
            test.setTitle(testDTO.getTitle());
            test.setStartTime(testDTO.getStartTime());
            test.setDuration(testDTO.getDuration());

            questionService.deleteQuestionsByTestId(id);

            List<Question> questions = testDTO.getQuestions().stream().map(mapper::toEntity).toList();
            questions.forEach(question -> {
                question.setTest(test);
                List<Choice> choices = question.getChoices();
                choices.forEach(choice -> {
                    choice.setQuestion(question);
                });
//                System.out.println(question.getChoices().getClass());
//                question.getChoices().removeAll(question.getChoices());
                question.setChoices(new ArrayList<>());
                question.getChoices().addAll(choices);
//                choiceRepository.saveAll(choices);
            });

            test.getQuestions().removeAll(test.getQuestions());
//            test.setQuestions(new ArrayList<>());
            test.getQuestions().addAll(questions);
//            questionRepository.saveAll(questions);
//            questionRepository.deleteAll(test.getQuestions());

            return testRepository.save(test);
        } else {
            throw new NoSuchElementException("Test with ID " + testDTO.getId() + " not found");
        }
    }

    public Test getTestByCode(String code) {
        return testRepository.findByCode(code).orElseThrow(() -> new RuntimeException("test not found"));
    }

    @Transactional
    public void deleteTestById(String username, Long id) {
        Test test = getUserTestById(username, id).get();
        questionService.deleteQuestionsByTestId(id);
        testRepository.delete(test);
    }
}
