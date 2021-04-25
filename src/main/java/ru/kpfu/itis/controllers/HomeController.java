package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

@Component
public class HomeController implements Initializable {

    @Autowired
    ConfigurableApplicationContext springContext;

    @FXML
    private Button beginButton;

    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Кнопка "Начать игру"
        beginButton.setOnAction(event -> {
            beginButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/question.fxml"));
            fxmlLoader.setControllerFactory(springContext::getBean);
            try {
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Игра началась!");
                stage.setScene(new Scene(root));
                stage.setMaximized(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        //Кнопка "Выход"
        exitButton.setOnAction(event -> {
            exit(0);

        });
    }
}
