package ru.kpfu.itis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.repository.QuestionRepository;

@ComponentScan("ru.kpfu.itis.models")
@ComponentScan("ru.kpfu.itis.repository")
@Service
public class QuestionService {
    @Autowired
    ConfigurableApplicationContext springContext;

    private static Question existingQuestion;

    @Autowired
    private QuestionRepository questionRepository;

    public Question getNewQuestion(){
        questionRepository = springContext.getBean(QuestionRepository.class);
        existingQuestion = questionRepository.getQuestion();
        return existingQuestion;
    }

    public Question getExistQuestion(){
        return existingQuestion;
    }

    public void addAll(){
        questionRepository.addAll();
    }
}
