package com.e01.quiz.service;


import com.e01.quiz.dto.AnswerResponse;
import com.e01.quiz.dto.QuestionResponse;
import com.e01.quiz.dto.Questiondto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public abstract class QuestionService {
    abstract QuestionResponse createQuestion(QuestionResponse questionResponse);
    public abstract Set<Questiondto> getAllQuestions();
    abstract QuestionResponse getQuestionById(long id);
    abstract QuestionResponse updateAnswer(AnswerResponse answerDto, long id);
    abstract String deleteQuestionById(long id);


}