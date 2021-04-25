package ru.kpfu.itis.repository;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.Question;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepository {
    private static List<Question> questions  = new ArrayList();
    private static int count = 0;

    public void addAll(){
        questions.add(new Question(0,"src/main/assets/img/q1-1.jpg", "src/main/assets/img/q1-2.jpg","src/main/assets/img/q1-3.jpg","src/main/assets/img/q1-4.jpg","СОБАКА"));
        questions.add(new Question(0,"src/main/assets/img/q2-1.jpg", "src/main/assets/img/q2-2.jpg","src/main/assets/img/q2-3.jpg","src/main/assets/img/q2-4.jpg","ТУБЕТЕЙ"));
        questions.add(new Question(0,"src/main/assets/img/q3-1.jpg", "src/main/assets/img/q3-2.jpg","src/main/assets/img/q3-3.jpg","src/main/assets/img/q3-4.jpg","ПИТЬ"));
        questions.add(new Question(0,"src/main/assets/img/q4-1.jpg", "src/main/assets/img/q4-2.jpg","src/main/assets/img/q4-3.jpg","src/main/assets/img/q4-4.jpg","ОРХИДЕЯ"));
        questions.add(new Question(0,"src/main/assets/img/q5-1.jpg", "src/main/assets/img/q5-2.jpg","src/main/assets/img/q5-3.jpg","src/main/assets/img/q5-4.jpg","ЛАМПА"));
        questions.add(new Question(0,"src/main/assets/img/q6-1.jpg", "src/main/assets/img/q6-2.jpg","src/main/assets/img/q6-3.jpg","src/main/assets/img/q6-4.jpg","КРЫЖОВНИК"));
        questions.add(new Question(0,"src/main/assets/img/q7-1.jpg", "src/main/assets/img/q7-2.jpg","src/main/assets/img/q7-3.jpg","src/main/assets/img/q7-4.jpg","КАПИБАРА"));
        questions.add(new Question(0,"src/main/assets/img/q8-1.jpg", "src/main/assets/img/q8-2.jpg","src/main/assets/img/q8-3.jpg","src/main/assets/img/q8-4.jpg","ДРУЖБА"));
        questions.add(new Question(0,"src/main/assets/img/q9-1.jpg", "src/main/assets/img/q9-2.jpg","src/main/assets/img/q9-3.jpg","src/main/assets/img/q9-4.jpg","АХЕГАО"));
    }

    public Question getQuestion(){
        Question question = questions.get(count);
        count++;
        return question;
    }
}
