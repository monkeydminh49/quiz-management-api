package com.e01.quiz.service;

import com.e01.quiz.entity.Choice;
import com.e01.quiz.entity.Question;
import com.e01.quiz.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChoiceService {
    @Autowired
    private ChoiceRepository repository;


    public void saveChoices(List<Choice> choices, Question question) {
        choices.forEach(choice -> choice.setQuestion(question));
        repository.saveAll(choices);
    }

    @Transactional
    public void deleteChoicesByQuestionId(Long id) {
        List<Choice> choices = repository.findAllByQuestionId(id);
        repository.deleteAll(choices);
    }
}


