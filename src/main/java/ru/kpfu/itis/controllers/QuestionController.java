package ru.kpfu.itis.controllers;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.client.HelloService;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.services.QuestionService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class QuestionController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private HelloService helloService;

    private String word;

    @Autowired
    private QuestionService service;

    private Question q;

    @Autowired
    ConfigurableApplicationContext springContext;

    @FXML
    private ImageView pic1, pic2, pic3, pic4;

    @FXML
    private Button deleteLast, menuButton, level, stage;

    @FXML
    private Button[] letters;

    @FXML
    private Canvas canvas;

    @FXML
    private Button letter1, letter2, letter3, letter4, letter5, letter6, letter7, letter8,
            letter9, letter10, letter11, letter12, letter13, letter14, letter15, letter16,
            letter17, letter18, letter19, letter20, letter21, letter22, letter23, letter24,
            letter25, letter26, letter27, letter28, letter29, letter30, letter31, letter32;

    private static int levelCounter = 2;

    //Устанавливаем надпись с номером уровня
    private void setLevel (int lvl) {
        level.setText("Уровень " + lvl);
    }
    //Устанавливаем надпись с номером этапа
    private void setStage (int stg) {
        stage.setText("Этап " + stg);
    }
    //Опустошить поле ввода (когда рестартается уровень, например)
    private void clearAnswerField () {
        graphicsContext.clearRect(0, 0, 1000, 1000);
    }

    private void update(int lvl, int stg) {
        //Тут взависимости от lvl и stg грузим нужную картинку и слово из массива
        q = service.getNewQuestion();
        File file1 = new File(q.getImg1());
        File file2 = new File(q.getImg2());
        File file3 = new File(q.getImg3());
        File file4 = new File(q.getImg4());

        pic1.setImage(new Image(file1.toURI().toString()));
        pic2.setImage(new Image(file2.toURI().toString()));
        pic3.setImage(new Image(file3.toURI().toString()));
        pic4.setImage(new Image(file4.toURI().toString()));

        setLevel(lvl);
        setStage(stg);
        clearAnswerField();


        setLevel(lvl);
        setStage(stg);
        clearAnswerField();
    }

    @FXML
    private void check(){
        helloService.setName(word);

        helloService.setOnSucceeded(event -> {
            FXMLLoader fxmlLoader = null;
            boolean flag = false;
            if (helloService.getValue().equals("true")){
                //вызов успешного модального окна
                System.out.println("true");
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/correctModal.fxml"));
                flag = true;
            } else{
                //вызов неуспешного модального окна
                System.out.println("false");
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/wrongModal.fxml"));
            }
            fxmlLoader.setControllerFactory(springContext::getBean);
            try {
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("yoyoyoyoyo broooo");
                stage.setScene(new Scene(root, 600, 600));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(flag){
                update(1,levelCounter++);
            }
            graphicsContext.clearRect(0, 0, 1000, 1000);
            word = "";
        });

        helloService.setOnFailed(event ->
                log.error(
                        "Unable to say hello to " +
                                helloService.getException()
                )
        );

        helloService.restart();

    }

    private GraphicsContext graphicsContext;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicsContext= canvas.getGraphicsContext2D();
        graphicsContext.setFont(Font.font("Verdana", 25.0));
        word = "";
        canvas.setHeight(50);
        canvas.setWidth(500);

        update(1, 1);

        //Кнопка 'del' правее от букв, удаляет последний символ в строке
        deleteLast.setOnMouseClicked(event -> {
            word = word.substring(0, word.length() - 1);
            graphicsContext.clearRect(0, 0, 1000, 1000);
            graphicsContext.strokeText(word, 30, 35);
        });

        //Возвращение в главное меню, правый верхний угол игрового экрана
        menuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                menuButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/home.fxml"));
                fxmlLoader.setControllerFactory(springContext::getBean);
                try {
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Главное меню");
                    stage.setScene(new Scene(root));
                    stage.setMinHeight(800);
                    stage.setMinWidth(800);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });

        //Кнопка "Проверить" с подтверждением ответа, поверх игрового, появляется модальное окно

        //Наша клавиатура
        letters = new Button[] {letter1, letter2, letter3, letter4, letter5, letter6, letter7, letter8,
                letter9, letter10, letter11, letter12, letter13, letter14, letter15, letter16,
                letter17, letter18, letter19, letter20, letter21, letter22, letter23, letter24,
                letter25, letter26, letter27, letter28, letter29, letter30, letter31, letter32};
        //абилки для нашей клавиатуры
        for (Button but : letters) {
            but.setOnMouseClicked(event -> {
                word = word + but.getText();
                graphicsContext.clearRect(0, 0, 1000, 1000);
                graphicsContext.strokeText(word, 30, 30);
            });
        }
    }

    }